import sbt._

object Dependencies {

  object V {
    val slf4j = "1.7.13"
    val logBack = "1.1.3"
    val scalaLogging = "3.1.0"
    val akka = "2.4.2-RC1"
    val scopt = "3.3.0"
    val macwire = "2.2.2"
    val postgres = "9.4.1207"
    val flyway = "3.2.1"
    val h2 = "1.3.176"
    val scalike = "2.3.4"
    val json4s = "3.3.0"
    val mockito = "1.10.19"
    val scalaTest = "2.2.5"
    val akkaJson4s = "1.5.0"
  }

  val scopt = "com.github.scopt" %% "scopt" % V.scopt
  val macwire = "com.softwaremill.macwire" %% "macros" % V.macwire

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
  val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit-experimental" % V.akka % "test"
  val akkaStack = Seq(akkaHttp, akkaHttpTestKit)

  // logging

  val slf4jApi = "org.slf4j" % "slf4j-api" % V.slf4j
  val logBackClassic = "ch.qos.logback" % "logback-classic" % V.logBack
  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % V.scalaLogging

  val loggingStack = Seq(slf4jApi, logBackClassic, scalaLogging)

  // database

  val postgres = "org.postgresql" % "postgresql" % V.postgres
  val flyway = "org.flywaydb" % "flyway-core" % V.flyway
  val h2 = "com.h2database" % "h2" % V.h2 % "test"
  val scalike = "org.scalikejdbc" %% "scalikejdbc" % V.scalike

  val dbStack = Seq(postgres, flyway, h2, scalike)

  // testing

  val mockito = "org.mockito" % "mockito-all" % V.mockito % "test"
  val scalatest = "org.scalatest" %% "scalatest" % V.scalaTest % "test"

  val testStack = Seq(mockito, scalatest)

}