lazy val buildSettings = Seq(
  scalaVersion := "2.11.6",
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:experimental.macros",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Yinline-warnings",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture"),
  resolvers ++= Seq(
    "bintray/non" at "http://dl.bintray.com/non/maven",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")))

lazy val shapelessCats = project.in(file("."))
  .settings(moduleName := "shapeless-cats")
  .settings(buildSettings)
  .aggregate(core)

lazy val core = project
  .settings(moduleName := "shapeless-cats-core")
  .settings(buildSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.2.0",
      "org.spire-math" %% "cats-core" % "0.1.0-SNAPSHOT"))
