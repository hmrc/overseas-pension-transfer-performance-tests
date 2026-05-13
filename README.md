# overseas-pension-transfer-performance-tests

Performance test suite for the `overseas-pension-transfer-frontend` service, using performance-test-runner.

## Pre-requisites

### Services
Start the docker desktop application (and make sure the mongodb is running on the docker)

Start `OVERSEAS_PENSION_TRANSFER_ALL` services as follows:

```bash
sm2 --start OVERSEAS_PENSION_TRANSFER_ALL
```

### Logging

The default log level for all HTTP requests is set to `WARN`. Configure [logback.xml](src/test/resources/logback.xml) to update this if required.

### WARNING

Do **NOT** run a full performance test against staging from your local machine. Please execute your job from the dashboard in Performance Jenkins.

### Tests

Run smoke test (locally) as follows:

```bash
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

Run full performance test (locally) as follows:

```bash
sbt -DrunLocal=true gatling:test

or

./run_local.sh
```

Run smoke test (staging) as follows:

```bash
sbt -Dperftest.runSmokeTest=true -DrunLocal=false gatling:test
```

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
