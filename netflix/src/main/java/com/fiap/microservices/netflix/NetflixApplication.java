package com.fiap.microservices.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NetflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixApplication.class, args);
	}

}
