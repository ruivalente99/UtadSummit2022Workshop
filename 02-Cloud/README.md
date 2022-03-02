# Cloud Service

![alt](https://img.shields.io/static/v1?label=Language&message=Java&color=orange)
![alt](https://img.shields.io/static/v1?label=Technologies&message=Kafka,gRPC&color=yellow)

## Overview

- Simulates a cloud microservice with some random core processing of data.
- It listens to a Kafka topic with events produced by an edge application and stores them in a database.
- It also exposes a gRPC method to retrieve the stored data's contents.
- See file `dev.env` for application configurations.
- Overload sensitive configurations adding a `local.env` file (not versioned!).
- See file `workshop.proto` for gRPC message contracts' definitions.

## Debug / Development

**1. Have your .env files configured**

https://plugins.jetbrains.com/plugin/7861-envfile

**2. Compile the protobuf file(s)**

In IntelliJ: Maven slide in the right -> Plugins -> protobuf -> protobuf:compile-custom -> mouse right click -> Run Maven Build

**3. Build and Run**

```bash
$ docker-compose up -d --build
```
