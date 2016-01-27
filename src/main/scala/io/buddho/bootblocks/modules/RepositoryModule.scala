package io.buddho.bootblocks.modules

import io.buddho.bootblocks.security.repository.UserRepository


trait RepositoryModule {
  this: ConfigModule =>

  lazy val userRepo: UserRepository = new UserRepository()

}
