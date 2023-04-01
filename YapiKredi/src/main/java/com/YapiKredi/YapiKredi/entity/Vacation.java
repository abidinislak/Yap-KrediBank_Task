package com.YapiKredi.YapiKredi.entity;


import com.YapiKredi.YapiKredi.util.Onay;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vacation extends BaseEntity {


    private Onay onay;

    private LocalDate startDate;
    private LocalDate endtDate;



    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;












}
