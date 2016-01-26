package io.buddho.bootblocks

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging
import io.buddho.bootblocks.config.Config
import io.buddho.bootblocks.modules.{ConfigModule, AkkaModule, RoutesModule}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}


trait RuntimeModules extends RoutesModule with AkkaModule with ConfigModule


class Boot extends StrictLogging {

  def start(cfg: Config): (Future[ServerBinding], RuntimeModules) = {

    val modules = new RuntimeModules {

      override val config: Config = cfg

      override implicit lazy val system: ActorSystem = ActorSystem("main")
      override implicit def executionContext: ExecutionContext = system.dispatcher
      override implicit lazy val materializer: Materializer = ActorMaterializer()

    }

    implicit val ec = modules.system
    implicit val mat = modules.materializer

    (Http().bindAndHandle(
      modules.routes,
      modules.config.server.host,
      modules.config.server.port),

      modules)
  }

}


object Boot extends App with StrictLogging {

  val config = Config(ConfigFactory.load(), args) match {
    case None => Config.help(); sys.exit(1)
    case Some(c) => c
  }

  val (binding: Future[ServerBinding], modules: RuntimeModules) = new Boot().start(config)

  implicit val ec = modules.executionContext

  binding.onComplete {
    case Success(b) =>
      logger.info(s"Server started on ${b.localAddress}")
      sys.addShutdownHook {
        b.unbind()
        modules.system.shutdown()
        logger.info("Server stopped")
      }
    case Failure(e) =>
      logger.error(s"Cannot start server.", e)
      sys.addShutdownHook {
        modules.system.shutdown()
        logger.info("Server stopped")
      }
  }

}
