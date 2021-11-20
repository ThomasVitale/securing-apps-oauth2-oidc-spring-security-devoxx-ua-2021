# Securing applications with OAuth2 and OIDC using Spring Security (Devoxx Ukraine 2021)

Source code and examples from my presentation at Devoxx Ukraine 2021

## Prerequisites

To run all the examples, you need to install the following tools:

* [Java 17](https://adoptium.net)
* [Docker](https://www.docker.com)

## Usage

The sample applications rely on Redis and Keycloak. You can run them as containers with the following command:

```shell
$ docker-compose up -d
```

Both Spring Boot applications can be run locally with this command:

```shell
$ ./gradlew bootRun
```
