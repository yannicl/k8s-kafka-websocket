#!/bin/bash
docker build --build-arg JAR_FILE=target/kafka-interaction-0.0.1-SNAPSHOT.jar --tag kafka-interaction .
