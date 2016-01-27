package io.buddho.bootblocks.security

import java.util.UUID

case class Authority(authority: String)

case class User(id: Option[UUID] = None, username: String, email: String, password: String, authorities: Set[Authority] = Set.empty)
