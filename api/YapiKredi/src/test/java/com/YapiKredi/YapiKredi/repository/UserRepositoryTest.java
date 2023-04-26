package com.YapiKredi.YapiKredi.repository;

import com.YapiKredi.YapiKredi.entity.User_H;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;

    @Test
    void saveUser() {

        User_H entity = new User_H();

        entity.setUserName("first user");

        entity.setStartDate(LocalDate.now());

        User_H saved = userRepository.save(entity);

        assertTrue(saved.getId() > 0);

    }
}