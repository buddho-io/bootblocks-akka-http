package io.buddho.bootblocks.modules

import akka.http.scaladsl.server.Route
import io.buddho.bootblocks.api.BaseApi

import com.softwaremill.macwire._
import io.buddho.bootblocks.security.api.AdminApi


trait RoutesModule {
  this: AkkaModule with ConfigModule with RepositoryModule =>

  private val admin: AdminApi = wire[AdminApi]

  private val api: BaseApi = wire[BaseApi]


  val routes: Route = api.routes

}
