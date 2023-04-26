package com.YapiKredi.YapiKredi.service;

import com.YapiKredi.YapiKredi.dto.Request_Dto;
import com.YapiKredi.YapiKredi.dto.ResponeseApi_Dto;
import com.YapiKredi.YapiKredi.dto.Vacat.Vacation_Dto;
import com.YapiKredi.YapiKredi.entity.Advance;
import com.YapiKredi.YapiKredi.entity.User_H;
import com.YapiKredi.YapiKredi.entity.Vacation;
import com.YapiKredi.YapiKredi.exceptions.ResourceNotFoundException;
import com.YapiKredi.YapiKredi.repository.VacationRepository;
import com.YapiKredi.YapiKredi.util.Onay;
import com.YapiKredi.YapiKredi.util.ResponseApi;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
public class VacationService {


    @Autowired
    AdvanceService advanceService;

    @Autowired
    VacationRepository vacationRepository;
    @Autowired
    UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public List<Vacation> findAll() {

        List<Vacation> result = vacationRepository.findAll();

        List<Vacation_Dto> dtos = result
                .stream()
                .map(user -> modelMapper.map(user, Vacation_Dto.class))
                .collect(Collectors.toList());


        return result;

    }

    //    public ResponseApi saveVacation(int userid, LocalDate startDate, int endDate) {
    public ResponeseApi_Dto saveVacation(Request_Dto requestDto) {

        int userid = requestDto.getUserid();
        LocalDate startDate;
        startDate = requestDto.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int endDate = requestDto.getAmount();


        User_H userEntity = userService.findById(userid);

        LocalDate theday = startDate;

        long daysDiff = Math.abs((Period.between(LocalDate.now(), startDate)).getDays());

        //    User is new
        if (ChronoUnit.DAYS.between(userEntity.getStartDate(), startDate) < 365) {
            if (endDate > 5) endDate = 5;
            List<Advance> userAdvences = advanceService.findCustomUser(userEntity.getId());
            if (userAdvences != null || userAdvences.size() > 0) {
                int advance = userAdvences.stream().mapToInt(x -> x.getAmount()).sum();

                if (advance + endDate > 5) endDate = 5 - advance;
                theday = calculateEndDate(startDate, endDate);

                ResponseApi result = savevacationProcess(userEntity, startDate, theday, false);

                ResponeseApi_Dto returnresult = modelMapper.map(result, ResponeseApi_Dto.class);

                return returnresult;

            }


        } else if (ChronoUnit.DAYS.between(userEntity.getStartDate(), startDate) > 365 && ChronoUnit.DAYS.between(userEntity.getStartDate(), startDate) < 730) {

            // look for Advances
            List<Advance> userAdvences = advanceService.findCustomUser(userEntity.getId());
            if (userAdvences != null || userAdvences.size() > 0) {


                int advance = userAdvences.stream().mapToInt(x -> x.getAmount()).sum();

                if (advance > 0) endDate = endDate - (endDate - (15 - advance));

                if (endDate > 0) {

                    endDate = exceptVacations(userid, endDate, startDate);
                    if (endDate > 0) {
                        theday = calculateEndDate(startDate, endDate);


                        ResponseApi result = savevacationProcess(userEntity, startDate, theday, false);

                        ResponeseApi_Dto returnresult = modelMapper.map(result, ResponeseApi_Dto.class);

                        return returnresult;

                    }


                }


            }


        } else if (ChronoUnit.DAYS.between(userEntity.getStartDate(), startDate) > 730 && ChronoUnit.DAYS.between(userEntity.getStartDate(), startDate) < 1825) {


            if (endDate > 15) endDate = 15;
            endDate = exceptVacations(userid, endDate, startDate);

            theday = calculateEndDate(startDate, endDate);

            ResponseApi result = savevacationProcess(userEntity, startDate, theday, false);

            ResponeseApi_Dto returnresult = modelMapper.map(result, ResponeseApi_Dto.class);

            return returnresult;


        } else if (ChronoUnit.DAYS.between(userEntity.getStartDate(), startDate) > 1825 && ChronoUnit.DAYS.between(userEntity.getStartDate(), startDate) < 3650) {


            if (endDate > 15) endDate = 18;

            theday = calculateEndDate(startDate, endDate);

            ResponseApi result = savevacationProcess(userEntity, startDate, theday, false);

            ResponeseApi_Dto returnresult = modelMapper.map(result, ResponeseApi_Dto.class);

            return returnresult;


        } else if (ChronoUnit.DAYS.between(userEntity.getStartDate(), startDate) > 3650) {


            if (endDate > 15) endDate = 24;

            theday = calculateEndDate(startDate, endDate);

            ResponseApi result = savevacationProcess(userEntity, startDate, theday, false);

            var returnresult = modelMapper.map(result, ResponeseApi_Dto.class);

            return returnresult;


        }

        return new ResponeseApi_Dto();
    }


//    Utils


    int exceptVacations(int userid, int amount, LocalDate date) {

        List<Vacation> usedVacations = vacationRepository.findCuctomByUser(userid, date.with(firstDayOfYear()), date.with(lastDayOfYear()));


        int vacationsCount = usedVacations.stream().mapToInt(x -> (int) Duration.between(x.getStartDate(), x.getEndtDate()).toDays()).sum();

        if (vacationsCount > 0) amount = amount - vacationsCount;

        return amount;
    }

    ResponseApi savevacationProcess(User_H user, LocalDate start, LocalDate end, Boolean isAdvanced) {

        Vacation entity = new Vacation();
        entity.setUser(user);
        entity.setStartDate(start);
        entity.setEndtDate(end);
        entity.setOnay(Onay.OnayBekliyor);


        Vacation savedVacation = vacationRepository.save(entity);

        if (savedVacation.getId() > 0 && isAdvanced) {
            Advance advanceEntity = new Advance();
            advanceEntity.setUser(user);
            advanceEntity.setAmount((int) Duration.between(start, end).toDays());
            Advance savedAdvance = advanceService.save(advanceEntity);
        }

        ResponseApi result = new ResponseApi();
        result.setMessage("Vacation Applied Succesfully");

        return result;


    }

    LocalDate calculateEndDate(LocalDate date, int amount) {

        LocalDate result = date;

        for (int i = 0; i < amount; ) {

            if (isWeekendDay(result)) {

                result = result.plusDays(1);

            } else {

                result = result.plusDays(1);
                i++;

            }


        }


        return result;
    }


    boolean isWeekendDay(LocalDate date) {


        //Can be done database
        return ((date.getDayOfWeek() == DayOfWeek.SATURDAY) || (date.getDayOfWeek() == DayOfWeek.SUNDAY)


                || date.isEqual(LocalDate.of(date.getYear(), 01, 01)) || date.isEqual(LocalDate.of(date.getYear(), 3, 23)) || date.isEqual(LocalDate.of(date.getYear(), 4, 01)) || date.isEqual(LocalDate.of(date.getYear(), 4, 19))

        );

    }


    public ResponseApi approveVacation(int userid, int vacationid) {

        User_H user = userService.findById(userid);


        if (!user.getAdmin()) return new ResponseApi("YOU are not auotoirized to this");

        else {

            Vacation entity = vacationRepository.approveVacation(vacationid);

            return new ResponseApi("YOu approved vcation succesfully");

        }


    }

    public void saveVacationPure(Vacation_Dto vacationDto) {

        Vacation entity = modelMapper.map(vacationDto, Vacation.class);

        vacationRepository.save(entity);

    }

    public Vacation_Dto findById(Integer id) {


        Vacation entity = vacationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vacation not foun by id" + id));
        return modelMapper.map(entity, Vacation_Dto.class);

    }
}
