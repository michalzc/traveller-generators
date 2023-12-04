val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Traveller Generators",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    scalacOptions += "-feature",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.10.0",
      "org.scalacheck" %% "scalacheck" % "1.17.0",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
      "ch.qos.logback" % "logback-classic" % "1.4.14",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )
