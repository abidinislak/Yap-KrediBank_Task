package com.YapiKredi.YapiKredi.repository;

import com.YapiKredi.YapiKredi.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;

    @Test
    void saveUser(){

        User entity=new User();

        entity.setUserName("first user");

        entity.setStartDate(LocalDate.now());

        User saved=userRepository.save(entity);

        assertTrue(saved.getId()>0);

    }
}