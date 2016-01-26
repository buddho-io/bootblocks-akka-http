package io.buddho.bootblocks.modules

import akka.actor.ActorSystem
import akka.stream.Materializer

import scala.concurrent.ExecutionContext


trait AkkaModule {

  implicit def executionContext: ExecutionContext
  implicit val system: ActorSystem
  implicit val materializer: Materializer

}
