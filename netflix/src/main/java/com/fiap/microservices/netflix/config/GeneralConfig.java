package com.fiap.microservices.netflix.config;

import java.io.Serializable;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeneralConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7249523954304974664L;

	@Bean
	@LoadBalanced
	public RestTemplate loadBalanced() {
		return new RestTemplate();
	}
	
	@Bean
	@Primary
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
