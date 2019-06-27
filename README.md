# Divorce Validation Service [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

[![Build Status](https://travis-ci.org/hmcts/spring-boot-template.svg?branch=master)](https://travis-ci.org/hmcts/spring-boot-template)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5afacddc65a84464b4f84bd0b72f118b)](https://www.codacy.com/app/HMCTS/div-validation-service)

This application validates the case data based on the validation rules supplied.

## Setup

**Prerequisites**

- [JDK 8](https://www.oracle.com/java)
- [Docker](https://www.docker.com)

**Building**

The project uses [Gradle](https://gradle.org) as a build tool but you don't have to install it locally since there is a
`./gradlew` wrapper script.

To build project please execute the following command:

```bash
    ./gradlew build
```

**Running**

First you need to create distribution by executing following command:

```bash
    ./gradlew installDist
```


When the distribution has been created in `build/install/div-validaton-serivce` directory,
you can run the application by executing following command:

```bash
    ./gradlew bootRun
```

As a result the following container(s) will get created and started:
 - long living container for API application exposing port `4008`

(`./gradlew bootRun` will stop at 75% but this is expected and you should still be able to access it)


## Testing

**Mutation tests**

To run all mutation tests execute the following command:

```bash
    ./gradlew pitest
```


**Unit tests**

To run all unit tests please execute following command:

```bash
    ./gradlew test
```

**Coding style tests**

To run all checks (including unit tests) please execute following command:

```bash
    ./gradlew check
```
## Developing

**API documentation**

API documentation is provided with Swagger:
 - `http://localhost:4008/swagger-ui.html` - UI to interact with the API resources

**Versioning**

We use [SemVer](http://semver.org/) for versioning.
For the versions available, see the tags on this repository.

**Standard API**

We follow [RESTful API standards](https://hmcts.github.io/restful-api-standards/).

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
