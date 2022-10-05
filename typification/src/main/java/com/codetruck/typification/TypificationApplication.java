package com.codetruck.typification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TypificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypificationApplication.class, args);
	}

}
