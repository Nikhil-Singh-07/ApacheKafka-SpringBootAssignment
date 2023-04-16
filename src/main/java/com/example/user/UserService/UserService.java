package com.example.user.UserService;


import com.example.user.UserEntity.UserEntity;
import com.example.user.UserRepository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private static Logger logger
            = LoggerFactory.getLogger(
            UserService.class);


    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> retrieveAllData(){
        List<UserEntity> totalUser= userRepository.findAll();
        return totalUser;
    }

    public UserEntity saveAllData(UserEntity user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            logger.info("Sorry, data is unavailable", e.getMessage());
            return null;
        }
    }
}
