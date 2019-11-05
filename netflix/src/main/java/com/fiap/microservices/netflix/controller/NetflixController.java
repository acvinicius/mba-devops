package com.fiap.microservices.netflix.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@RequestMapping(value="/movie/all", method = RequestMethod.GET)
	public Iterable<Movie> getAllMovies () {
		return this.service.getAllMovies();
	}

	@RequestMapping(value="/movie", method = RequestMethod.GET)
	public Movie getMovieById (@RequestParam("id") Long id) {
		return this.service.getMovieById(id);
	}
	
	@RequestMapping(value="/movie", method = RequestMethod.PUT)
	public Object save (@RequestParam("name") String name, @RequestParam("type") String type, @RequestParam("details") String details) {
		Movie movie = new Movie();
		movie.setName(name);
		movie.setType(type);
		movie.setDetails(details);
		movie.setCountLiked(0L);
		movie.setCountWatched(0L);
		movie.setHasProblem(false);
		return this.service.save(movie);
	}

	@RequestMapping(value="/movie/type", method = RequestMethod.GET)
	public List<Movie> getMoviesByType (@RequestParam("type") String type) {
		return this.service.getMoviesByType(type);
	}

	@RequestMapping(value="/movie/liked", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void setMovieAsLiked (@RequestParam("movie_id") Long movieId) {
		this.service.setMovieAsLiked(movieId);
	}

	@RequestMapping(value="/movie/watched", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void setMovieAsWatched (@RequestParam("movie_id") Long movieId) {
		this.service.setMyWatchedMovies(movieId);
	}
	
	@RequestMapping(value="/movie/type/most_viewed", method = RequestMethod.GET)
	public List<Movie> getMoviesMostViwedByType (@RequestParam("type") String type) {
		return this.service.getMoviesMostViwedByType(type);
	}
	
	@RequestMapping(value="/movie/report_problem_status", method = RequestMethod.PUT)
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void reportProblemStatus (@RequestParam("movie_id") Long id, @RequestParam("has_problem") Boolean status) {
		this.service.reportProblemStatus(id, status);
	}
	
	@RequestMapping(value="/movie/details", method = RequestMethod.GET)
	public List<Movie> getMoviesByDetails (@RequestParam("details") String details) {
		return this.service.getMoviesByDetails(details);
	}
}
