package com.fiap.microservices.netflix.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fiap.microservices.netflix.model.Movie;

@Repository
public interface MoviesRepository extends CrudRepository<Movie, String> {

	List<Movie> findByName(String name);
	
	Movie findById(Long id);
	
	List<Movie> findByType(String type);
	
	<S extends Movie> S save(S entity);
	
}
