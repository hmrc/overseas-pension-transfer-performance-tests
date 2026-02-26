lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .settings(
    name := "overseas-pension-transfer-performance-tests",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "3.3.4",
    crossScalaVersions := Seq("2.13.16", "3.3.4"),
    scalacOptions ++= Seq("-language:postfixOps"),
    libraryDependencies ++= Seq(
      "uk.gov.hmrc"          %% "performance-test-runner"   % "6.3.0"         % Test
    ) ++ Dependencies.test,
    // Enabling sbt-auto-build plugin provides DefaultBuildSettings with default `testOptions` from `sbt-settings` plugin.
    // These testOptions are not compatible with `sbt gatling:test`. So we have to override testOptions here.
    Test / testOptions := Seq.empty
  )
