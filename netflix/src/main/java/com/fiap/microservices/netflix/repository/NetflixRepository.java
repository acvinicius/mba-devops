package com.fiap.microservices.netflix.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fiap.microservices.netflix.model.Movie;

@Repository
public class NetflixRepository implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5329995539401766934L;

	public List<Movie> getMoviesByType(String type) {
		return null;
		
	}

	
}
