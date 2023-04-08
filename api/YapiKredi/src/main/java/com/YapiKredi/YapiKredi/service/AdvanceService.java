package com.YapiKredi.YapiKredi.service;

import com.YapiKredi.YapiKredi.entity.Advance;
import com.YapiKredi.YapiKredi.repository.AdvanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvanceService {


    @Autowired
    AdvanceRepository advanceRepository;

    public Advance save(Advance advanceEntity) {


        return  advanceRepository.save(advanceEntity);
    }

    public List<Advance> findCustomUser(int id) {
        return advanceRepository.findCustomUser(id);
    }
}
