package com.YapiKredi.YapiKredi.repository;

import com.YapiKredi.YapiKredi.entity.Advance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvanceRepository extends JpaRepository<Advance ,Integer> {


    @Query("SELECT A FROM Advance A LEFT JOIN  A.user  U WHERE U.id= :id")
    List<Advance> findCustomUser(@Param("id") int id);


}
