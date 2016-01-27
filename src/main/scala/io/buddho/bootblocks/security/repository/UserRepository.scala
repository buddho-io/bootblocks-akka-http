package io.buddho.bootblocks.security.repository

import java.util.UUID

import io.buddho.bootblocks.security.{Authority, User}

import scala.collection.mutable
import scala.concurrent.Future


class UserRepository {

  private lazy val users = mutable.ArrayBuffer[User](
    User(username ="joe", email = "joe@email.com", password = "TODO encrypt", authorities = Set(Authority("ROLE_USER")))
  )

  def getUsers: Future[List[User]] = {
    Future.successful(users.toList)
  }

  def deleteUser(id: UUID): Future[Option[User]] = {
    val index = users.indexWhere(_.id.contains(id))
    if (index > -1) {
      Future.successful(Some(users.remove(index)))
    } else {
      Future.successful(None)
    }
  }

}
