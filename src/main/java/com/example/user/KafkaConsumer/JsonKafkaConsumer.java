package com.example.user.KafkaConsumer;

import com.example.user.UserEntity.UserEntity;
import com.example.user.UserService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    private UserService userService;

    public JsonKafkaConsumer(UserService userService){
        this.userService = userService;
    }



    @KafkaListener(topics = "newjsontopic", groupId = "myGroup")
    public void jsonConsumer(UserEntity user){
        logger.info(String.format("Message Receive -> %s", user));
        userService.saveAllData(user);
    }
}
