package com.fiap.microservices.netflix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "myMovies")
public class MyMovies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5072500446137394457L;

	@ManyToMany
	private Movie movie;
	@Column(name = "liked", nullable = true)
	private boolean liked;
	@ManyToMany
	private User user;
	
}