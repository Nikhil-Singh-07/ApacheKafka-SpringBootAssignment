package com.example.user.UserController;

import com.example.user.KafkaProducer.JsonKafkaProducer;
import com.example.user.KafkaProducer.StringKafkaProducer;
import com.example.user.UserEntity.UserEntity;
import com.example.user.UserService.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private StringKafkaProducer stringKafkaProducer;
    @Autowired
    private UserService userService;

    @Autowired
    private JsonKafkaProducer jsonKafkaProducer;

    public UserController(UserService userService, StringKafkaProducer stringKafkaProducer, JsonKafkaProducer jsonKafkaProducer){
        this.userService = userService;
        this.stringKafkaProducer = stringKafkaProducer;
        this.jsonKafkaProducer = jsonKafkaProducer;
    }


    //$ bin/kafka-console-consumer.sh --topic newTopic --from-beginning --bootstrap-server localhost:9092


    //http://localhost:8080/api/v1/user/sendMessage?message=Hi Nikhil Singh
    @GetMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        stringKafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Sent Message Successfully!");
    }

    @PostMapping("/sendJsonMessage")
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserEntity user){
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Sent Json Message Successfully!");
    }

    @GetMapping("/retrieveAllUsers")
    public ResponseEntity<?> getAllUsers(){
        List<UserEntity> result = userService.retrieveAllData();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/createUsers")
    public ResponseEntity<?> saveUsersData(UserEntity user){
        try{
            return new ResponseEntity<>(userService.saveAllData(user), HttpStatus.CREATED);
        }
        catch (Exception e){
            logger.info("Sorry, we can not save the data", e.getMessage());
            return new ResponseEntity<>(userService.saveAllData(user), HttpStatus.NOT_FOUND);
        }
    }

}
