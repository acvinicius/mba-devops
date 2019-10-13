package com.fiap.microservices.netflix.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8965167201516621171L;

	private String username;
	private String password;
	private List<MyMovies> myWatchedMovies;
	private List<MyMovies> myFutureMovies;
	
}
