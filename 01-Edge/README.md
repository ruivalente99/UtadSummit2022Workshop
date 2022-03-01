# Edge App

![alt](https://img.shields.io/static/v1?label=Language&message=Java&color=orange)
![alt](https://img.shields.io/static/v1?label=Technologies&message=Kafka&color=yellow)

## Overview

- Simulates an edge application by randomly generating events sent by a sensor (just generating a random number).
- It then builds a structured message to send those events to the cloud microservices through a Kafka topic.
- See file `dev.env` for application configurations.
- Overload sensitive configurations adding a `local.env` file (not versioned!).
- See file `workshop.proto` for gRPC message contracts definitions.

## Debug / Development

**1. Have your .env files configured**

https://plugins.jetbrains.com/plugin/7861-envfile

**2. Compile the protobuf file(s)**

In IntelliJ: Maven slide in the right -> Plugins -> protobuf -> protobuf:compile-custom -> mouse right click -> Run Maven Build

**3. Build and Run**

```bash
$ docker-compose up -d --build
```
