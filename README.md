# UTAD Summit 2022 - Workshop

Here you will find the code used in the UTAD's Summit 2022 Workshop.

This README.md also contains some of the theory and reasoning behind this particular choice of code and microservices.

---

## Pre-requisites
1. You need Apache Kafka, with a `scans` topic. In the workshop, [Confluent Cloud](https://confluent.cloud/) was used.
2. You need Docker to deploy all the microservices. In the workshop, [Docker Desktop](https://www.docker.com/products/docker-desktop) was used.

---

## Overview

The microservices available here are an example subset of a more complex and bigger architecture used in Neoception for the development of our products. I am pretty sure that you will find online separate examples of each portion of the code - here we just glue them in a consistent way to fulfill a specific purpose.

The big picture is that we need to capture events captured by sensors, process them, store processed information, and then make it available to the end user. For that purpose we use a set of technologies, mostly open source.

Said that, the four microservices are described below, along with their pedagogical reason for their inclusion in this workshop. The folders in this repository match the headers of the respective sections.

All microservices can be deployed as Docker containers running in a single computer, and an Internet connection is required because two of them need to read or write to Apache Kafka topics (unless you spin a local Apache Kafka container and do all the needed tweaks to have the code using it - I strongly encourage you to explore that path if you feel confident!) 

---

## 01-Edge

Hardware devices, like sensors and actuators, are usually pieces with a single purpose. Sensors are expected to capture some real world physical data and convert it in electrical signals that can then be further processed (usually, sensors already output bytes that can be handled by software). Actuators too, but in the reverse way: do some specific real world action given some command.

Then, some edge application send/gets those bytes (typically proprietary or non-standard formats) and converts the raw data to/from structured information decoupled from hardware specifics that the rest of the system undertands.

This way, if we change the devices - and potentially the way we have to communicate with the sensors through code - we can just tweak the edge application to do the proper conversions to the common data structure, and the rest of the system's code remains untouched.

Edge applications usually also run inside gateways placed next to the devices and typically communicate the information to other components of the system in producer/consumer ways, as they usually run in separate servers with more CPU, RAM and disk than the gateways (either physical or cloud servers). This way we can have the edge application quickly drop the information in a queue and forget, therefore guaranteeing that the information is not lost due to latency or servers down.

In the workshop, we didn't use real sensors - for the purpose of the demonstration of the producer part of the producer/consumer system and introduce Apache Kafka, we developed an edge app that randomly generates a number (let's imagine it is a temperature measure, or some identifier of a carrier scanned in a RFID reader) and then embeds that number in a Kafka message, sending it to a Kafka topic. The random number is generated at random times to simulate a non-regular flow of data.

---

## 02-Cloud

In the other side of the producer/consumer schema, we developed a pure cloud microservice with a sidecar database that just listens to the same Kafka topic where the edge application is producing data, getting the messages, extracting the information inside them and storing it in the database, along with some meta-information like the timestamp of that message.

It will also provide a public method exposed through gRPC that collects the data stored in the database and returns it to the caller.

This component will serve to illustrate microservices with a specific purpose, that uses as raw material data coming from Kafka topics, that arrive at irregular times, picking one message a time, processing it (in this case, the processing is just storing it in a database) and then be available to provide the collected information so far to whoever needs it.

At this point, in the workshop, we close the producer/consumer subject and talk a bit about why using gRPC and the Postgres database we used.

---

## 03-API

It is also another cloud microservice, but now implementing a REST API. The single purpose in this demonstration is to have a Swagger UI exposing a single method that any user can directly call using, for example, Postman opened in a browser. The method will in turn connect to the previous cloud microservice, calling through gRPC its method that returns the list of the recorded data so far.

In the workshop, we took this opportunity to talk about REST API, and why it is important to use API's for external communication instead of allowing direct calls to the core microservices or directly listening to the internal Kafka topics. Also talk a bit about Swagger UI and how useful this kind of tools are for human users. 

And, of course, close the gRPC topic by exemplifying a call.

---

## 04-UI

Last but not least, calling API's even through Postman is not a very user-friendly human interface. It is enough for developers doing quick tests, or third party applications integrating with our application, but not for the non-tech user.

Enter the user interface application. It was single page with a table showing the last messages stored by the cloud microservice (indeed, this UI is also a cloud microservice, but it could also be a mobile app, if needed). The page contained a cyclic auto-refresh code that just called the API method, retrieving the information and rendering the table in the screen.

Then, users can just look to the screen without doing nothing, and from time to time as the random messages are generated, they automatically appear in the screen.

We ended the workshop here, speaking a bit about the user interfaces being designed in such a way that they reduce the number of mouse clicks and keyboard typing, plus some worrying in having stuff clean and intuitive. Users not liking the experience of using our applications are users that will start looking to the more shinny pages of the competition even though they have less features or are a bit more buggy.

---

## Notes

- In the workshop, the Apache Kafka topic was configured to maintain data for a week. Therefore, once a message is more than one week in the topic, it got deleted.
  