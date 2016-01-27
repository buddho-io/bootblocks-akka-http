package io.buddho.bootblocks.security.api

import java.util.UUID

import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.Materializer
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import io.buddho.bootblocks.security.User
import io.buddho.bootblocks.security.json.SecuritySerializers
import io.buddho.bootblocks.security.repository.UserRepository
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class AdminApi(userRepo: UserRepository, ec: ExecutionContext, mat: Materializer) extends Directives with Json4sSupport {

    implicit val serialization = Serialization
    implicit val formats = DefaultFormats ++ SecuritySerializers.all

    lazy val routes: Route = {
      pathPrefix("users") {
        get {
          complete(userRepo.getUsers)
        } ~
        post {
          entity(as[User]) { user: User =>
            ???
          }
        } ~
        path(JavaUUID) { id: UUID =>
          put {
            entity(as[User]) { user: User =>
              ???
            }
          } ~
          delete {
            ???
          }
        }
      }
    }

}
