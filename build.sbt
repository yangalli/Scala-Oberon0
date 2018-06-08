scalaVersion := "2.12.4"

name := "Mini linguagem de Tecnicas de Programacao"
organization := "br.unb.cic"
version := "1.0"

libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

/* libraryDependencies += "org.scalameta" %% "scalameta" % "1.7.0"

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalameta" % "paradise_2.12.2" % "3.0.0-M8") */

parallelExecution in Test := false
