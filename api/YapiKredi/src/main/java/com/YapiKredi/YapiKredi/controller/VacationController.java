package com.YapiKredi.YapiKredi.controller;


import com.YapiKredi.YapiKredi.dto.Request_Dto;
import com.YapiKredi.YapiKredi.dto.ResponeseApi_Dto;
import com.YapiKredi.YapiKredi.dto.testDto;
import com.YapiKredi.YapiKredi.entity.Vacation;
import com.YapiKredi.YapiKredi.repository.VacationRepository;
import com.YapiKredi.YapiKredi.service.VacationService;
import com.YapiKredi.YapiKredi.util.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VacationController {


    @Autowired
    VacationRepository vacationRepository;

    @Autowired
    VacationService vacationService;


    @GetMapping("/vacation/getAll")
    public ResponseEntity<List<Vacation>> findAll() {

List<Vacation> vacations=  vacationService.findAll();
Vacation aaa=vacations.stream().findAny().get();
        return ResponseEntity.status(HttpStatus.OK).body(vacations);
    }



    @PostMapping("/vacation/vacationApply")
    public ResponseEntity<ResponeseApi_Dto> vaactionApply(@RequestBody Request_Dto requestDto) {


        return ResponseEntity.status(HttpStatus.OK).body(vacationService.saveVacation(requestDto));
    }

    @PostMapping("/vacation/approve/{id}")

    public ResponseEntity<ResponseApi> approveVacation(@RequestParam int userid,@RequestParam int vacationid){



        return ResponseEntity.status(HttpStatus.OK).body(vacationService.approveVacation(userid,vacationid));
    }



@PostMapping("/test")
    public void test(@RequestBody testDto aaa){
    System.err.println("ç...."+aaa);
}



}
