package com.fiap.microservices.netflix.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyMovies implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5072500446137394457L;

	private Movie movie;
	private boolean liked;
	
}
