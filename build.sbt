ThisBuild / version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.18"  // Compatible with Spark 3.x
// Apache Spark dependency

lazy val root = (project in file("."))
  .settings(
    name := "scalatest"
  )
version := "0.1"
// Define Spark version as a variable to keep it consistent
val sparkVersion = "3.4.1"

// Add Spark dependencies
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.4.1" % Test,
  "org.apache.spark" %% "spark-sql" % "3.4.1" % Test,
  "org.scalatest" %% "scalatest" % "3.2.17" % Test
)

// Add ScalaTest if you need it for testing
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test

dependencyOverrides += "org.apache.spark" %% "spark-core" % sparkVersion
dependencyOverrides += "org.apache.spark" %% "spark-sql" % sparkVersion

