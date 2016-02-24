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

package io.buddho.bootblocks.security.repository

import java.time.{ZoneOffset, ZonedDateTime}
import java.util.UUID

import io.buddho.bootblocks.security.User
import scalikejdbc._

import scala.concurrent.{ExecutionContext, Future}


class UserRepository(executionContext: ExecutionContext) {

  implicit private val ec = executionContext

  private val u = User.syntax("u")

  def getAll(max: Int = 25, offset: Int = 0)(implicit session: DBSession = AutoSession): Future[List[User]] = Future {
    withSQL {
      select.from(User as u).limit(max).offset(offset)
    }.map(User(u)).list().apply()
  }

  def get(id: UUID)(implicit session: DBSession = AutoSession): Future[Option[User]] = Future {
    withSQL { select.from(User as u).where.eq(User.column.id, id) }.map(User(u)).single().apply()
  }

  def remove(id: UUID)(implicit session: DBSession = AutoSession): Future[Int] = Future {
    withSQL { delete.from(User).where.eq(User.column.id, id) }.update().apply()
  }

  def save(user: User)(implicit session: DBSession = AutoSession): Future[User] = Future {
    val now = ZonedDateTime.now(ZoneOffset.UTC)
    if (user.id.isDefined) {
      withSQL {
        update(User).set(
          User.column.username -> user.username,
          User.column.email -> user.email,
          User.column.password -> user.password,
          User.column.enabled -> user.enabled,
          User.column.accountNonLocked -> user.accountNonLocked,
          User.column.accountNonExpired -> user.accountNonExpired,
          User.column.credentialsNonExpired -> user.credentialsNonExpired,
          User.column.updatedAt -> now)
          .where.eq(User.column.id, user.id.get)
      }.update().apply()
      user.copy(updatedAt = now)
    } else {
      val id = UUID.randomUUID()
      withSQL {
        insert.into(User).namedValues(
          User.column.id -> id,
          User.column.username -> user.username,
          User.column.email -> user.email,
          User.column.password -> user.password,
          User.column.enabled -> user.enabled,
          User.column.accountNonLocked -> user.accountNonLocked,
          User.column.accountNonExpired -> user.accountNonExpired,
          User.column.credentialsNonExpired -> user.credentialsNonExpired,
          User.column.createdAt -> now,
          User.column.updatedAt -> now
        )
      }.update().apply()
      user.copy(id = Some(id), createdAt = now, updatedAt = now)
    }
  }
}