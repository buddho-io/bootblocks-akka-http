package io.buddho.bootblocks.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class BaseApi {

  lazy val routes: Route = {
    pathPrefix("api") {
      ???
    }
  }

}
