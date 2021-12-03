package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AppRoute {
    @Bean
    public RouterFunction<ServerResponse> route(FormHandler formHandler) {

        return RouterFunctions.route()
                .GET("/form", formHandler::sampleForm)
                .POST("/form", formHandler::displayFormData)
                .build();
    }
}
