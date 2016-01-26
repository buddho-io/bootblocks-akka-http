

name := "bootblocks-akka-http"

organization := "io.buddho.bootblocks"

scalaVersion := "2.11.7"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

libraryDependencies ++= Seq(
  D.scopt,
  D.macwire) ++
  D.akkaStack ++
  D.loggingStack ++
  D.dbStack ++
  D.testStack

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}


lazy val D = new {

  val V = new {
    val slf4j = "1.7.13"
    val logBack = "1.1.3"
    val scalaLogging = "3.1.0"
    val akkaHttp = "2.0.2"
    val scopt = "3.3.0"
    val macwire = "2.2.2"
    val postgres = "9.4.1207"
    val flyway = "3.2.1"
    val h2 = "1.3.176"
    val scalike = "2.3.4"
    val mockito = "1.10.19"
    val scalaTest = "2.2.5"
  }

  val scopt = "com.github.scopt" %% "scopt" % V.scopt
  val macwire = "com.softwaremill.macwire" %% "macros" % V.macwire

  // akka

  val akkaHttp = "com.typesafe.akka" %% "akka-http-experimental" % V.akkaHttp
  val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit-experimental" % V.akkaHttp % "test"
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


