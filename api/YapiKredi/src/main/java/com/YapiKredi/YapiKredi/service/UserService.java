package com.YapiKredi.YapiKredi.service;

import com.YapiKredi.YapiKredi.entity.User_H;
import com.YapiKredi.YapiKredi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User_H findById(int userid) {
        return userRepository.findById(userid).orElseThrow(() -> new RuntimeException("user not found wit " + userid));
    }

    public User_H save(User_H entity) {

        return userRepository.save(entity);
    }
}
