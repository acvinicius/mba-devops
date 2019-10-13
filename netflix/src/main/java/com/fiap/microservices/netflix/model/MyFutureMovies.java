package com.fiap.microservices.netflix.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "myFutureMovies")
public class MyFutureMovies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5072500446137394457L;

	@ManyToMany
	private Movie movie;
	@ManyToMany
	private User user;
	
}
