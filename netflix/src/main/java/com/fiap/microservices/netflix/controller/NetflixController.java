package com.fiap.microservices.netflix.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private MoviesDetailsService service;
	
	@RequestMapping(value="/movies/type", method = RequestMethod.GET)
	public List<Movie> getMoviesByType (@PathVariable("type") String type) {
		return this.service.getMoviesByType(type);
	}
	
	@RequestMapping(value="/movies", method = RequestMethod.PUT)
	public Object save (@PathVariable("movie") Movie movie) {
		return this.service.save(movie);
	}
	
	@RequestMapping(value="/movies/liked", method = RequestMethod.GET)
	public List<Movie> getMyLikedMovies () {
		return this.service.getMyLikedMovies();
	}
	
	@RequestMapping(value="/movies/watched", method = RequestMethod.GET)
	public List<Movie> getMyWatchedMovies () {
		return this.service.getMyLikedMovies();
	}

	@RequestMapping(value="/movies/future", method = RequestMethod.GET)
	public List<Movie> getMyFutureWatchedMovies () {
		return this.service.getMyLikedMovies();
	}
	
	@RequestMapping(value="/movies/liked", method = RequestMethod.PATCH)
	public void setMovieAsLiked () {
		this.service.setMovieAsLiked();
	}
	
	@RequestMapping(value="/movies/liked", method = RequestMethod.GET)
	public List<Movie> getMoviesMostViwedByType () {
		return this.service.getMoviesMostViwedByType();
	}
	
	@RequestMapping(value="/movies/liked", method = RequestMethod.GET)
	public List<Movie> getMovieById () {
		return this.service.getMoviesMostViwedByType();
	}
}
