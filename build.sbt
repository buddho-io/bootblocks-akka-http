
lazy val root = (project in file("."))
  .enablePlugins(GitVersioning, GitBranchPrompt)
  .settings(
    name := "bootblocks-akka-http",
    organization := "io.buddho.bootblocks",

    git.baseVersion := "1.0",

    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-deprecation"),

    javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint"),

    libraryDependencies ++=
      Dependencies.configStack ++
      Dependencies.jsonStack ++
      Dependencies.commonStack ++
      Dependencies.akkaStack ++
      Dependencies.loggingStack ++
      Dependencies.dbStack ++
      Dependencies.testStack,

    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),

    initialize := {
      val _ = initialize.value
      if (sys.props("java.specification.version") != "1.8")
        sys.error("Java 8 is required for this project.")
    }
  )


