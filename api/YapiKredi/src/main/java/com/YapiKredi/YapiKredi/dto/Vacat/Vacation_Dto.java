package com.YapiKredi.YapiKredi.dto.Vacat;

import com.YapiKredi.YapiKredi.entity.User;
import com.YapiKredi.YapiKredi.entity.Vacation;
import com.YapiKredi.YapiKredi.util.Onay;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class Vacation_Dto {




    private Onay onay;

    private LocalDate startDate;
    private LocalDate endtDate;




    private User user;


}
