package com.YapiKredi.YapiKredi.util;

import lombok.Data;

import java.util.Date;

@Data
public class ErroObject {


    private Integer statusCode;

    private String message;

    private Date timestamp;
}



