package com.fiap.microservices.netflix.servicedesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicedeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicedeskApplication.class, args);
	}

}
