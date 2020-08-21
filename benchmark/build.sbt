import Dependencies._

enablePlugins(GatlingPlugin)

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.cogrammer",
      scalaVersion := "2.12.10",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "Quarkus vs Spring Boot Benchmark",
    libraryDependencies ++= gatling
  )
