package com.mitrais.scrummit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class Application {

	private static Class<Application> app = Application.class;
	
	public static void main(String[] args) {
        SpringApplication.run(app, args);
    }
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder appBuilder) {
		return appBuilder.sources(app);
	}
}
