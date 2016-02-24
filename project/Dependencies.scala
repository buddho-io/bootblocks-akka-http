import sbt._

object Dependencies {

  object V {
    val slf4j = "1.7.16"
    val logBack = "1.1.5"
    val scalaLogging = "3.1.0"
    val akka = "2.4.2"
    val scopt = "3.4.0"
    val configs = "0.3.0"
    val macwire = "2.2.2"
    val postgres = "9.4.1207"
    val flyway = "3.2.1"
    val h2 = "1.3.176"
    val scalike = "2.3.5"
    val json4s = "3.3.0"
    val mockito = "1.10.19"
    val scalaTest = "2.2.5"
    val akkaJson4s = "1.5.2"
    val dbcp2 = "2.1.1"
    val flywayConfig = "0.1-SNAPSHOT"
  }

  // config

  val scopt = "com.github.scopt" %% "scopt" % V.scopt
  val macwire = "com.softwaremill.macwire" %% "macros" % V.macwire
  val configs = "com.github.kxbmap" %% "configs" % V.configs

  val configStack = Seq(scopt, macwire, configs)

  // common

  val java8Compat = "org.scala-lang.modules" %% "scala-java8-compat" % "0.7.0"

  val commonStack = Seq(java8Compat)

  // json
  val json4SCore = "org.json4s" %% "json4s-core" % V.json4s
  val json4SJackson = "org.json4s" %% "json4s-jackson" % V.json4s
  val json4SExt = "org.json4s" %% "json4s-ext" % V.json4s
  val akkaJson4s = "de.heikoseeberger" %% "akka-http-json4s" % V.akkaJson4s

  val jsonStack = Seq(json4SCore, json4SJackson, json4SExt, akkaJson4s)

  // akka

  val akkaHttp = "com.typesafe.akka" %% "akka-http-experimental" % V.akka
  val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % V.akka % "test"
  val akkaStack = Seq(akkaHttp, akkaHttpTestKit)

  // logging

  val slf4jApi = "org.slf4j" % "slf4j-api" % V.slf4j
  val logBackClassic = "ch.qos.logback" % "logback-classic" % V.logBack
  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % V.scalaLogging

  val loggingStack = Seq(slf4jApi, logBackClassic, scalaLogging)

  // database

  val postgres = "org.postgresql" % "postgresql" % V.postgres
  val flyway = "org.flywaydb" % "flyway-core" % V.flyway
  val flywayConfig = "io.buddho" %% "flyway-config" % V.flywayConfig
  val scalike = "org.scalikejdbc" %% "scalikejdbc" % V.scalike
  val scalikeConfig = "org.scalikejdbc" %% "scalikejdbc-config" % V.scalike
  val scalikeJsr310 = "org.scalikejdbc" %% "scalikejdbc-jsr310" % V.scalike
  val dbcp2 = "org.apache.commons" % "commons-dbcp2" % V.dbcp2
  val h2 = "com.h2database" % "h2" % V.h2 % "test"

  val dbStack = Seq(postgres, flyway, flywayConfig, h2, scalike, scalikeConfig, scalikeJsr310, dbcp2)

  // testing

  val mockito = "org.mockito" % "mockito-all" % V.mockito % "test"
  val scalatest = "org.scalatest" %% "scalatest" % V.scalaTest % "test"

  val testStack = Seq(mockito, scalatest)

}