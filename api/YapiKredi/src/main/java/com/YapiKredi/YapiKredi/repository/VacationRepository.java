package com.YapiKredi.YapiKredi.repository;

import com.YapiKredi.YapiKredi.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VacationRepository extends JpaRepository <Vacation,Integer> {

    @Query(" SELECT V FROM Vacation V LEFT JOIN V.user U WHERE  U.id= :id and  V.onay= com.YapiKredi.YapiKredi.util.Onay.Onayladi and   V.startDate      BETWEEN :start and :last ")
    List<Vacation> findCuctomByUser(@Param("id") int id, @Param("start") LocalDate start,@Param("last") LocalDate last);



    @Modifying
    @Query(value = "UPDATE Vacation SET onay=2 WHERE Vacation.id= ?1",nativeQuery = true)
    Vacation approveVacation( int id);
}
