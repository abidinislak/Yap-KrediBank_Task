package com.YapiKredi.YapiKredi.repository;

import com.YapiKredi.YapiKredi.entity.Advance;
import com.YapiKredi.YapiKredi.entity.User_H;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AdvanceRepositoryTest {

    @Autowired
    AdvanceRepository advanceRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    void saveAdvance() {

        User_H entityUser = userRepository.findById(1).orElseThrow(() -> new RuntimeException("usr not fund"));

        Advance entity = new Advance();
        entity.setUser(entityUser);
        entity.setAmount(1);

        Advance sveduser = advanceRepository.save(entity);

        assertTrue(sveduser.getId() > 0);


    }


}