package com.yannic.interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Kafka2WebSocketInteraction {

    @Autowired
    SocketHandler socketHandler;

    @KafkaListener(topics = "${financial.transactions.topic.name}", containerFactory = "financialTransactionsKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        if (message.startsWith("Time:")) {
            long time = Long.valueOf(message.substring(5));
            System.out.println("In flight time:" + (System.currentTimeMillis() - time) + "ms");
            socketHandler.forwardTransactions(message);
        } else {
            System.out.println("Received Message: " + message + " from partition: " + partition);
        }
    }

}
