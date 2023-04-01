package com.YapiKredi.YapiKredi.controller;


import com.YapiKredi.YapiKredi.entity.Vacation;
import com.YapiKredi.YapiKredi.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/v1")
public class VacationController {



    @Autowired
    VacationService vacationService;


    @GetMapping("/vacation/getAll")
    public ResponseEntity<List<Vacation>> findAll(){


        return ResponseEntity.status(HttpStatus.OK).body(vacationService.findAll());
    }



}
