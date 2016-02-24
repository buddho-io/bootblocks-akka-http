// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

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
