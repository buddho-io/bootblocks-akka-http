package io.buddho.bootblocks.modules

import akka.http.scaladsl.server.Route
import io.buddho.bootblocks.api.BaseApi

import com.softwaremill.macwire._


trait RoutesModule {
  this: AkkaModule with ConfigModule =>

  private val api = wire[BaseApi]

  val routes: Route = api.routes

}
