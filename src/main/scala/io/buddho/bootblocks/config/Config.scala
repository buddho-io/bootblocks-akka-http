package io.buddho.bootblocks.config


import com.typesafe.config.{Config => TypesafeConfig}
import scopt.OptionParser


case class Config(server: ServerConfig)

case class ServerConfig(host: String, port: Int)


object Keys {
  object service {
    object server {
      val Host = "service.server.host"
      val Port = "service.server.port"
    }
  }
}


object Config {
  import Keys._

  lazy val parser = new OptionParser[Config]("bootblocks-service") {
    head("bootblocks-service", "0.1.x")
  }

  def apply(config: TypesafeConfig): Config = {
    Config(
      server = ServerConfig(
        host = config.getString(service.server.Host),
        port = config.getInt(service.server.Port)
      )
    )
  }

  def apply(config: TypesafeConfig, args: Seq[String]): Option[Config] = {
    val base = Config(config)
    parser.parse(args, base)
  }

  def help(): Unit = parser.showUsageAsError

}
