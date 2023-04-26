package com.YapiKredi.YapiKredi.dto.Vacat;

import com.YapiKredi.YapiKredi.entity.User_H;
import com.YapiKredi.YapiKredi.util.Onay;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Vacation_Dto {


    private Onay onay;

    private LocalDate startDate;
    private LocalDate endtDate;


    private User_H user;


}
