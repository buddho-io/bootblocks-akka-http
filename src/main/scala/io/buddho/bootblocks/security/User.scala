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

package io.buddho.bootblocks.security

import java.time.{ZoneOffset, ZonedDateTime}
import java.util.UUID

import scalikejdbc._, jsr310._

case class Authority(authority: String)

object Authority extends SQLSyntaxSupport[Authority]

case class User(id: Option[UUID] = None, username: String, email: String,
                password: String, enabled: Boolean = true, accountNonLocked: Boolean = true,
                credentialsNonExpired: Boolean = true, accountNonExpired: Boolean = true,
                authorities: Set[Authority] = Set.empty, createdAt: ZonedDateTime = null, updatedAt: ZonedDateTime = null)

object User extends SQLSyntaxSupport[User] {
  def apply(u: SyntaxProvider[User])(rs: WrappedResultSet): User = apply(u.resultName)(rs)
  def apply(u: ResultName[User])(rs: WrappedResultSet): User = new User(
    id = rs.stringOpt(u.id).map(UUID.fromString),
    username = rs.string(u.username),
    email = rs.string(u.email),
    password = rs.string(u.password),
    enabled = rs.boolean(u.enabled),
    accountNonLocked = rs.boolean(u.accountNonLocked),
    credentialsNonExpired = rs.boolean(u.credentialsNonExpired),
    accountNonExpired = rs.boolean(u.accountNonExpired),
    createdAt = rs.zonedDateTime(u.createdAt).withZoneSameInstant(ZoneOffset.UTC),
    updatedAt = rs.zonedDateTime(u.updatedAt).withZoneSameInstant(ZoneOffset.UTC))
}
