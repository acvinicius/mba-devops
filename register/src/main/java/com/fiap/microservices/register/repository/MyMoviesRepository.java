package com.fiap.microservices.register.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fiap.microservices.register.model.MyMovies;

public interface MyMoviesRepository extends CrudRepository<MyMovies, String> {

	List<MyMovies> findById(Long id);
	
	List<MyMovies> findByMovieId(Long movieId);
	
	List<MyMovies> findByUserId(Long userId);
	
	<S extends MyMovies> S save(MyMovies movies);
	
	List<MyMovies> findByUserIdAndLiked(Long userId, Boolean liked);
	
	List<MyMovies> findByUserIdAndWatched(Long userId, Boolean watched);

	List<MyMovies> findByUserIdAndFuture(Long userId, Boolean future);
	
	MyMovies findByUserIdAndMovieId(Long userId, Long movieId);
	
}
