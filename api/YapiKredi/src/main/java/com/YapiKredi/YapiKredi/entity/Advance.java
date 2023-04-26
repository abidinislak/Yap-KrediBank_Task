package com.YapiKredi.YapiKredi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Advance extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User_H user;
    private int amount;
}
