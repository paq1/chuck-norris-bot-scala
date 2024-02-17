ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.4.0"

lazy val root = (project in file("."))
  .aggregate(bot, core)

lazy val bot = (project in file("src/bot"))
  .settings(
    name := "ChuckNorrisBot-bot",
    libraryDependencies ++= List(
      "net.dv8tion" % "JDA" % "5.0.0-beta.19",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
      "ch.qos.logback" % "logback-classic" % "1.4.7",
      "org.scala-lang" %% "toolkit" % "0.2.0"
    )
  )

lazy val core = (project in file("src/core"))
  .settings(
    name := "ChuckNorrisBot-core"
  )
