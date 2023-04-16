package com.example.user.KafkaProducer;


import com.example.user.UserEntity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, UserEntity> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, UserEntity> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(UserEntity user){
        logger.info(String.format("Message sent -> %s", user.toString()));
        Message<UserEntity> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, "newjsontopic").build();
        kafkaTemplate.send(message);
    }
}
