package com.example.user.KafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class StringKafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(StringKafkaProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public StringKafkaProducer(KafkaTemplate<String, String>kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){
        logger.info(String.format("Message Sent Successfully -> %s", message));
        kafkaTemplate.send("newTopic", message);
    }
}
