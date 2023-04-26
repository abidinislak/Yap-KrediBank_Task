package com.YapiKredi.YapiKredi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"message", "test"})
public class ResponeseApi_Dto {

    @JsonProperty("message")
    private String message;
    @JsonProperty("test")
    private int test;
}
