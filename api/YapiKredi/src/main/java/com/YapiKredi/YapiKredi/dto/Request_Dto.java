package com.YapiKredi.YapiKredi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"userid", "amount", "date"})
public class Request_Dto {

    @JsonProperty("username")
    private int userid;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("date")
    private String date;


}
