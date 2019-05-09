#!/bin/bash
cd kafka_2.12-2.2.0
bin/zookeeper-server-start.sh config/zookeeper.properties &
bin/kafka-server-start.sh config/server.properties &
cd ..
sleep 5
java -jar java/kafka-emitter/target/kafka-emitter-0.0.1-SNAPSHOT.jar &
java -jar java/kafka-interaction/target/kafka-interaction-0.0.1-SNAPSHOT.jar &

