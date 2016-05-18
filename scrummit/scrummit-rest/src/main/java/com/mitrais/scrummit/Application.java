package com.mitrais.scrummit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = { "com.mitrais.scrummit" }, includeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "com.mitrais.scrummit.dao"),
        @Filter(type = FilterType.REGEX, pattern = "com.mitrais.scrummit.bo"),
        @Filter(type = FilterType.REGEX, pattern = "com.mitrais.scrummit.config") })
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}