version := "1.0"

organization := "com.pka.scala"
scalaVersion := "2.13.3"
name := "scala-filesystem"

//resolvers += "Artima maven repo" at "http://repo.artima.com/releases"

//addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.3")
libraryDependencies ++=
  Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "org.scalatest" %% "scalatest" % "3.2.2" % Test,
    "com.vladsch.flexmark" % "flexmark-all" % "0.35.10" % "test",
    "org.scalatest" %% "scalatest-flatspec" % "3.2.7" % "test"
  )

testOptions in Test ++=
  Seq(
    Tests.Argument(TestFrameworks.ScalaTest, "-oSD"),
    Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")
  )