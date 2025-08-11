import uk.gov.hmrc.DefaultBuildSettings

ThisBuild / scalaVersion := "3.5.2"
ThisBuild / majorVersion := 0
ThisBuild / scalacOptions ++= Seq(
  "-Werror",
  "-Wconf:msg=Flag.*repeatedly:s"
)
ThisBuild / scalacOptions += "-nowarn" // Added help suppress warnings in migration. Must be removed when changes shown are complete
ThisBuild / scalafmtOnCompile := true

val appName = "individuals-other-income-api"

lazy val ItTest = config("it") extend Test

lazy val microservice = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .disablePlugins(JUnitXmlReportPlugin) // Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test(),
    scalacOptions ++= List(
      "-feature",
      "-Wconf:src=routes/.*:s"
    )
  )
  .settings(
    Compile / unmanagedResourceDirectories += baseDirectory.value / "resources"
  )
  .settings(majorVersion := 0)
  .settings(CodeCoverageSettings.settings *)
  .configs(ItTest)
  .settings(
    ItTest / fork                       := true,
    ItTest / unmanagedSourceDirectories := List((ItTest / baseDirectory).value / "it"),
    ItTest / unmanagedClasspath += baseDirectory.value / "resources",
    Runtime / unmanagedClasspath += baseDirectory.value / "resources",
    ItTest / javaOptions += "-Dlogger.resource=logback-test.xml",
    ItTest / parallelExecution := false,
  )
  .settings(PlayKeys.playDefaultPort := 7761)

dependencyUpdatesFilter -= moduleFilter(name = "bootstrap-backend-play-30")
dependencyUpdatesFilter -= moduleFilter(organization = "org.playframework")
dependencyUpdatesFilter -= moduleFilter(name = "simple-reactivemongo")
dependencyUpdatesFilter -= moduleFilter(name = "reactivemongo-test")
dependencyUpdatesFilter -= moduleFilter(name = "scala-library")
dependencyUpdatesFilter -= moduleFilter(name = "flexmark-all")
dependencyUpdatesFilter -= moduleFilter(name = "scalatestplus-play")
dependencyUpdatesFilter -= moduleFilter(name = "scalatestplus-scalacheck")
dependencyUpdatesFailBuild := true
