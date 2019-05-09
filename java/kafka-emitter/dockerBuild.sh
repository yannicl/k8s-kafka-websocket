#!/bin/bash
docker build --build-arg JAR_FILE=target/kafka-emitter-0.0.1-SNAPSHOT.jar --tag kafka-emitter .
