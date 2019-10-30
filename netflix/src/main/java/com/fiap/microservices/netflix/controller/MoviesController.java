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
public class MoviesController implements Serializable {

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
		return this.service.getMyWatchedMovies();
	}
	
	@RequestMapping(value="/movies/watched", method = RequestMethod.PATCH)
	public void setMyWatchedMovies (Long id) {
		this.service.setMyWatchedMovies(id);
	}

	@RequestMapping(value="/movies/future", method = RequestMethod.PATCH)
	public void setMyFutureWatchedMovies (Long id) {
		this.service.setMyFutureWatchedMovies(id);
	}

	@RequestMapping(value="/movies/future", method = RequestMethod.GET)
	public List<Movie> getMyFutureWatchedMovies () {
		return this.service.getMyFutureWatchedMovies();
	}
	
	@RequestMapping(value="/movies/liked", method = RequestMethod.PATCH)
	public void setMovieAsLiked (Long id) {
		this.service.setMovieAsLiked(id);
	}
	
	@RequestMapping(value="/movies/liked", method = RequestMethod.GET)
	public List<Movie> getMoviesMostViwedByType (String type) {
		return this.service.getMoviesMostViwedByType(type);
	}
	
	@RequestMapping(value="/movies/liked", method = RequestMethod.GET)
	public Movie getMovieById (Long id) {
		return this.service.getMovieById(id);
	}
	
	@RequestMapping(value="/movies/report_problem_status", method = RequestMethod.PATCH)
	public void reportProblemStatus (Long id, Boolean status) {
		this.service.reportProblemStatus(id, status);
	}
	
}
