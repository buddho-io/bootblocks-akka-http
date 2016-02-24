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

package io.buddho.blootblocks.security.repository.db


import com.typesafe.config.ConfigFactory
import io.buddho.bootblocks.security.User
import io.buddho.bootblocks.security.repository.UserRepository
import io.buddho.flyway
import io.buddho.flyway.FlywayConfig
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}
import scalikejdbc.config.DBs

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}


class UserRepositorySpec extends FlatSpec with Matchers with ScalaFutures with BeforeAndAfterAll {

  implicit val defaultPatience =
    PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  val config = ConfigFactory.load()
  implicit val ec = ExecutionContext.global
  val migrations = new flyway.Flyway(FlywayConfig(config) , ec)

  override protected def beforeAll() = {
    DBs.setupAll()
    migrations.migrate(throwOnFailure = true)
  }

  override protected def afterAll() = {
    migrations.clean(throwOnFailure = true)
  }

  def fixture = new {
    val repo = new UserRepository(ec)
  }

  "repository" should "create and get users" in {
    val f = fixture

    val users = Future.sequence((0 to 4).map( i =>
      f.repo.save(new User(username = s"test_$i", email = s"test+${1}@email.com", password = "secret"))
    )).futureValue

    users.size should equal(5)

    val user = f.repo.get(users(0).id.get).futureValue

    user.isDefined should be(true)
    user.get should equal(users(0))
  }

  it should "get paged user lists" in {
    val f = fixture

    val users = Future.sequence((0 to 50).map( i =>
      f.repo.save(new User(username = s"test_$i", email = s"test+${1}@email.com", password = "secret"))
    )).futureValue

    val page1 = f.repo.getAll(max = 10).futureValue
    val page2 = f.repo.getAll(max = 10, offset = 10).futureValue

    page1.size should be(10)
    page1 shouldNot equal(page2)
  }

//  it should "delete users" in {
//    fail()
//  }

}
