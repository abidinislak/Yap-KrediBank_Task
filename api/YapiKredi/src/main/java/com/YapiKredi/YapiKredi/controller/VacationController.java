package com.YapiKredi.YapiKredi.controller;


import com.YapiKredi.YapiKredi.dto.Request_Dto;
import com.YapiKredi.YapiKredi.dto.ResponeseApi_Dto;
import com.YapiKredi.YapiKredi.dto.testDto;
import com.YapiKredi.YapiKredi.entity.Vacation;
import com.YapiKredi.YapiKredi.repository.VacationRepository;
import com.YapiKredi.YapiKredi.service.VacationService;
import com.YapiKredi.YapiKredi.util.ResponseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(description = "Get all Applies", responses = {

            @ApiResponse(responseCode = "400", ref = "badrequest"),
            @ApiResponse(responseCode = "500", ref = "internalServerApi"),

            @ApiResponse(responseCode = "200", description = "Succesfuly gel all appplies", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(
                            value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully get all applies!\"}"
                    ),
            })),


    })
    public ResponseEntity<List<Vacation>> findAll() {

        List<Vacation> vacations = vacationService.findAll();
        Vacation aaa = vacations.stream().findAny().get();
        return ResponseEntity.status(HttpStatus.OK).body(vacations);
    }


    @PostMapping("/vacation/vacationApply")
    public ResponseEntity<ResponeseApi_Dto> vaactionApply(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(


            mediaType = "application/json", examples ={

                    @ExampleObject(
                            value = "{\"username\" : 200, \"amoutn\" : \"Ok!\", \"date\" :\"Successfully get all applies!\"}"
                    )
    }

    )) @RequestBody Request_Dto requestDto) {


        return ResponseEntity.status(HttpStatus.OK).body(vacationService.saveVacation(requestDto));
    }

    @PostMapping("/vacation/approve/{id}")

    public ResponseEntity<ResponseApi> approveVacation(@RequestParam int userid, @RequestParam int vacationid) {


        return ResponseEntity.status(HttpStatus.OK).body(vacationService.approveVacation(userid, vacationid));
    }


    @PostMapping("/test")
    public void test(@RequestBody testDto aaa) {
        System.err.println("ç...." + aaa);
    }


}
