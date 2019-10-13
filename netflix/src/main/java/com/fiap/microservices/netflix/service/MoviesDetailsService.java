package com.fiap.microservices.netflix.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.microservices.netflix.model.Movie;
import com.fiap.microservices.netflix.repository.NetflixRepository;

@Service
public class MoviesDetailsService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1592557918964883785L;
	
	@Autowired
	private NetflixRepository repository;

	public List<Movie> getMoviesByType(String type) {
		return this.repository.getMoviesByType(type);
	}

}
