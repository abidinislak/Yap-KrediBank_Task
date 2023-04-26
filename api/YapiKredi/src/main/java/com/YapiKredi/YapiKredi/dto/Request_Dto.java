package com.YapiKredi.YapiKredi.dto;


import com.YapiKredi.YapiKredi.validation.DateValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class Request_Dto {

    @JsonProperty("username")
    private int userid;
    @NotNull
    @JsonProperty("amount")
    private int amount;
    @NotNull

    @JsonProperty("date")
//    Custom Validation
    @DateValidation
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;


    @Min(value = 10, message = "at least must be 10")
    private int testforvalidation;
}
