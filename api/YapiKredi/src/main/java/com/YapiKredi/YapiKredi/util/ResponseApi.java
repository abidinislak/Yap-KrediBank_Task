package com.YapiKredi.YapiKredi.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi {
    public ResponseApi(String message) {
        this.message = message;
    }

    private String message;

    private int test;
}
