package com.YapiKredi.YapiKredi.util;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadJsonFeilToJsonObject   {


    public JSONObject read() throws IOException {



        String file="src/main/resources/openapi/response.json";
        String content=new String(Files.readAllBytes(Paths.get(file)));
        return new JSONObject(content);
    }
}
