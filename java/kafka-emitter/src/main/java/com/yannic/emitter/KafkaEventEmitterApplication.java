package com.yannic.emitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaEventEmitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaEventEmitterApplication.class, args);
    }

}
