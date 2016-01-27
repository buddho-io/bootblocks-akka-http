package io.buddho.bootblocks.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import io.buddho.bootblocks.security.api.AdminApi
import org.json4s.Serialization

import scala.concurrent.ExecutionContext

class BaseApi(ec: ExecutionContext, mat: Materializer, admin: AdminApi) {

  val executionContext = ec
  val materializer = mat

  lazy val routes: Route = {
    pathPrefix("api") {
      pathPrefix("admin") { admin.routes }
    }
  }

}
