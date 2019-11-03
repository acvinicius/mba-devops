package com.fiap.microservices.netflix.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.microservices.netflix.model.Movie;
import com.fiap.microservices.netflix.repository.MoviesRepository;

@Service
public class MoviesDetailsService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1592557918964883785L;
	
	@Autowired
	private MoviesRepository repository;
	
	public List<Movie> getMoviesByType(String type) {
		return this.repository.findByType(type);
	}

	public void setMovieAsLiked(Long id) {
		Movie movie = this.repository.findById(id);
		movie.setCountLiked(movie.getCountLiked() + 1L);
		this.repository.save(movie);
		//post em register service
		
//		this.restTemplate.exchange(url, method, requestEntity, responseType)
	}

	public List<Movie> getMoviesMostViwedByType(String type) {
		List<Movie> movies = new ArrayList<Movie>();
		movies = this.repository.findByType(type);
		//return this.repository.getMoviesMostViwedByType(type);
		return null;
	}

	public Object save(Movie movie) {
		return this.repository.save(movie);
	}

	public Movie getMovieById(Long id) {
		return this.repository.findById(id);
	}

	public void setMyWatchedMovies(Long id) {
		Movie movie = this.repository.findById(id);
		movie.setCountWatched(movie.getCountWatched() + 1L);
		this.repository.save(movie);
	}

	public void reportProblemStatus(Long id, Boolean status) {
		Movie movie = this.repository.findById(id);
		movie.setHasProblem(status);
		this.repository.save(movie);
	}

}
