package com.fiap.microservices.netflix.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.microservices.netflix.model.Movie;
import com.fiap.microservices.netflix.service.MoviesDetailsService;

@RestController
@RequestMapping(value="/v1")
public class NetflixController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1499412735506460046L;

	private MoviesDetailsService service;
	
	@RequestMapping(value="/{genero}", method = RequestMethod.GET)
	public List<Movie> getMoviesByGenero (@PathVariable("genero") String genero) {
		
		return service.getMoviesByGenero(genero);
	}
}
