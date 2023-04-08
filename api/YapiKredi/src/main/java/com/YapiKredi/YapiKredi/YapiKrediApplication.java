package com.YapiKredi.YapiKredi;

import com.YapiKredi.YapiKredi.dto.Vacat.Vacation_Dto;
import com.YapiKredi.YapiKredi.entity.User;
import com.YapiKredi.YapiKredi.entity.Vacation;
import com.YapiKredi.YapiKredi.service.UserService;
import com.YapiKredi.YapiKredi.service.VacationService;
import com.YapiKredi.YapiKredi.util.Onay;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
//@OpenAPIDefinition(
//
//        info = @Info(
//
//
//                title = "Yapı Kredi Bank Personel Management",
//                version = "0.0.1",
//                description = "This is Yapı Kredi Bank perosnel management project(Vacation)",
//                termsOfService = "java",
//                contact = @Contact(
//
//                        name = "Abidin IŞLAK",
//                        email = "abidinislak"
//                ),license = @License(name = "liense",
//        url = "abidinislak")
//        )
//
//)
@SpringBootApplication
public class YapiKrediApplication implements CommandLineRunner {
    @Autowired
    VacationService vacationService;

    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    public static void main(String[] args) {
        SpringApplication.run(YapiKrediApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User entity = new User();
        entity.setStartDate(LocalDate.of(2022, 01, 01));
        entity.setUserName("abididn");

        User returnUser = userService.save(entity);

        Vacation vacation = new Vacation();

        vacation.setUser(returnUser);
        vacation.setStartDate(LocalDate.of(2021, 01, 01));
        vacation.setEndtDate(LocalDate.of(2022, 01, 01));
        vacation.setOnay(Onay.OnayBekliyor);

        Vacation_Dto vacationDto = modelMapper.map(vacation, Vacation_Dto.class);
        vacationService.saveVacationPure(vacationDto);

        Vacation vacation2 = new Vacation();

        vacation2.setUser(returnUser);
        vacation2.setStartDate(LocalDate.of(2020, 02, 02));
        vacation2.setEndtDate(LocalDate.of(2020, 02, 02));
        vacation2.setOnay(Onay.OnayBekliyor);

        Vacation_Dto vacationDto2 = modelMapper.map(vacation, Vacation_Dto.class);
        vacationService.saveVacationPure(vacationDto2);

    }
}
