package com.fiap.microservices.netflix.config;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConfigDataSource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4719341982330566164L;

	@Bean
	public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.postgresql.Driver");
	    dataSource.setUrl("jdbc:postgresql:netflixdb");
	    dataSource.setUsername( "postgres" );
	    dataSource.setPassword( "100201" );
	    return dataSource;
	}
}
