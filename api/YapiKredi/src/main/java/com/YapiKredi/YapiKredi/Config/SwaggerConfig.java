package com.YapiKredi.YapiKredi.Config;


import com.YapiKredi.YapiKredi.util.ReadJsonFeilToJsonObject;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.io.IOException;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI baseOpenApi() throws IOException {
        ReadJsonFeilToJsonObject readJsonFeilToJsonObject = new ReadJsonFeilToJsonObject();
        ApiResponse badRequestApi = new ApiResponse().content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonFeilToJsonObject.read().get("badrequestresponse")).description("bad request"))));
        ApiResponse internalServerApi = new ApiResponse().content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(readJsonFeilToJsonObject.read().get("internalresvererror")).description("internalresvererror"))));
        Components components = new Components();
        components.addResponses("badrequest", badRequestApi);
        components.addResponses("internalServerApi", internalServerApi);
        return new OpenAPI().components(components).info(new Info().title("Yapı Kerde Task").version("0.0.1").description("Yapı Kredi bak task"));
    }

    @Bean
    public GroupedOpenApi postapi() {
        String[] paths = {"/api/**"};
        return GroupedOpenApi.builder().group("post").pathsToMatch(paths).build();
    }

    @Bean
    public GroupedOpenApi denemeapi() {
        String[] paths = {"/deneme/**"};
        return GroupedOpenApi.builder().group("Deneme").pathsToMatch(paths).build();

    }

}
