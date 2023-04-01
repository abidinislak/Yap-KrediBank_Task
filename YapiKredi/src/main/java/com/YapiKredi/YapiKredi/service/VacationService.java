package com.YapiKredi.YapiKredi.service;

import com.YapiKredi.YapiKredi.entity.Vacation;
import com.YapiKredi.YapiKredi.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.swing.tree.VariableHeightLayoutCache;

@Service
public class VacationService {

    @Autowired
    VacationRepository vacationRepository;


    public List<Vacation> findAll() {


        return vacationRepository.findAll();
    }
}
