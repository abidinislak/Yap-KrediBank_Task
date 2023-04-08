package com.YapiKredi.YapiKredi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User  extends BaseEntity {



    private String userName;


    private LocalDate startDate;


    private Boolean admin;



 @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Advance> advances=new HashSet<>();


}
