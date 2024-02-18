ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.0"

lazy val chuckNorrisBot = (project in file("."))
  .aggregate(bot, core)
  .settings {
    name := "ChuckNorrisBotScala"
  }

lazy val bot = (project in file("src/bot"))
  .dependsOn(core)
  .settings(
    name := "ChuckNorrisBot-bot",
    libraryDependencies ++= List(
      "net.dv8tion" % "JDA" % "5.0.0-beta.19",
      "com.typesafe" % "config" % "1.4.2",
      "ch.qos.logback" % "logback-classic" % "1.4.14",
      "org.scala-lang" %% "toolkit" % "0.2.0"
    )
  )

lazy val core = (project in file("src/core"))
  .settings(
    name := "ChuckNorrisBot-core",
    libraryDependencies ++= List(
      "org.typelevel" %% "cats-effect" % "3.5.3",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
    )
  )
