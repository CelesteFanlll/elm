package com.example.Achitecture.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Configuration
public class swaggerConfig {

//    http://localhost:9091/swagger-ui/index.html


    @Bean
    Docket xiaokeai() {
        return new Docket(DocumentationType.OAS_30).useDefaultResponseMessages(false)
                .produces(Stream.of("application/xml", "application/json").collect(Collectors.toSet())).select()
                .paths(PathSelectors.regex("/sys/.*")).build()
                .protocols(Stream.of("http", "https").collect(Collectors.toSet()));
    }

}