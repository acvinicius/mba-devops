package com.fiap.microservices.netflix.service;

import java.io.Serializable;
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

	public List<Movie> getMyLikedMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMovieAsLiked(Long id) {
		Movie movie = this.repository.findById(id);
		movie.setCountLiked(movie.getCountLiked() + 1L);
		this.repository.save(movie);
	}

	public List<Movie> getMoviesMostViwedByType(String type) {
		//return this.repository.getMoviesMostViwedByType(type);
		return null;
	}

	public Object save(Movie movie) {
		return this.repository.save(movie);
	}

	public List<Movie> getMyWatchedMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	public Movie getMovieById(Long id) {
		return this.repository.findById(id);
	}

	public List<Movie> getMyFutureWatchedMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMyWatchedMovies(Long id) {
		Movie movie = this.repository.findById(id);
		movie.setCountWatched(movie.getCountWatched() + 1L);
		this.repository.save(movie);
	}

	public void setMyFutureWatchedMovies(Long id) {
		
	}

	public void reportProblemStatus(Long id, Boolean status) {
		Movie movie = this.repository.findById(id);
		movie.setHasProblem(status);
		this.repository.save(movie);
	}

}
