package com.fiap.microservices.netflix.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<Movie> getMoviesByType (@PathVariable("type") String type) {
		return this.service.getMoviesByType(type);
	}

	@RequestMapping(value="/movie/type", method = RequestMethod.PUT)
	public void setMovieAsLiked (@PathVariable("movie_id") Long movieId) {
		this.service.setMovieAsLiked(movieId);
	}
	
	@RequestMapping(value="/movie/type/most_viewed", method = RequestMethod.GET)
	public List<Movie> getMoviesMostViwedByType (@PathVariable("type") String type) {
		return this.service.getMoviesMostViwedByType(type);
	}
	
	@RequestMapping(value="/movie/report_problem_status", method = RequestMethod.PUT)
	public void reportProblemStatus (@PathVariable("movie_id") Long id, @PathVariable("has_problem") Boolean status) {
		this.service.reportProblemStatus(id, status);
	}
	
}
