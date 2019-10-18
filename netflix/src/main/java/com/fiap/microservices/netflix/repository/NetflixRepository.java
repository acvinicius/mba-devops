package com.fiap.microservices.netflix.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fiap.microservices.netflix.model.Movie;

@Repository
public interface NetflixRepository extends CrudRepository<Movie, String> {


	public List<Movie> getMoviesByType(String type);

	<S> S save(Movie movie);
	
	
}
