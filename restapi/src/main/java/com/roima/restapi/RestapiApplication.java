package com.roima.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springdoc.core.configuration.SpringDocHateoasConfiguration.class, org.springdoc.core.configuration.SpringDocDataRestConfiguration.class})
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

}
