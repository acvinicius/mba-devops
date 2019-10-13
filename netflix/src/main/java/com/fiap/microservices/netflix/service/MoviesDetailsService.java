package com.fiap.microservices.netflix.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.microservices.netflix.model.Movie;

@Service
public class MoviesDetailsService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1592557918964883785L;

	public List<Movie> getMoviesByGenero(String genero) {
		// TODO Auto-generated method stub
		return null;
	}

}
