package com.example.user.KafkaConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StringKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(StringKafkaConsumer.class);

    @KafkaListener(topics = "newtopic", groupId = "myGroup")
    public void consumer(String message){
        logger.info(String.format("Message received -> %s", message));
    }

}
