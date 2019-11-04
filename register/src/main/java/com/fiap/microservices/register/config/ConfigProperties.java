package com.fiap.microservices.register.config;

import java.io.Serializable;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
public class ConfigProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2423140125660819949L;

	@JsonProperty("url.netflix.movie")
	private String urlLikedMovie;
	
}
