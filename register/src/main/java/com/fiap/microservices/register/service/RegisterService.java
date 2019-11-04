package com.fiap.microservices.register.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fiap.microservices.register.model.MyMovies;
import com.fiap.microservices.register.model.User;
import com.fiap.microservices.register.repository.MyMoviesRepository;
import com.fiap.microservices.register.repository.UsersRepository;

@Service
public class RegisterService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8808680048770720188L;

	@Autowired
	private UsersRepository repository;

	@Autowired
	private MyMoviesRepository moviesRepository;
	
	@Autowired
	@LoadBalanced
	private RestTemplate loadBalanced;
	
	private static final String URL_NETFLIX_MOVIE_LIKED="http://netflixservice/v1/movie/liked?movie_id={movie_id}&user_id{user_id}";
	private static final String URL_NETFLIX_MOVIE_WATCHED="http://netflixservice/v1/movie/watched?movie_id={movie_id}&user_id{user_id}";
	
	public Long save(User user) {
		user = this.repository.save(user);
		return user.getId();
	}

	public User find(Long userId) {
		return this.repository.findById(userId);
	}

	public void markAsWatched(Long userId, Long movieId) {
		MyMovies movie = this.moviesRepository.findByUserIdAndMovieId(userId, movieId);
		if (movie == null) {
			movie = new MyMovies();
			movie.setMovieId(movieId);
			movie.setUserId(userId);
			movie.setFuture(false);
			movie.setWatched(true);
			movie.setLiked(false);
		} else {
			movie.setWatched(true);
			movie.setFuture(false);
		}
		this.moviesRepository.save(movie);
	}

	public void setMyFutureWatchedMovies(Long userId, Long movieId) {
		MyMovies movie = this.moviesRepository.findByUserIdAndMovieId(userId, movieId);
		if (movie == null) {
			movie = new MyMovies();
			movie.setMovieId(movieId);
			movie.setUserId(userId);
			movie.setFuture(true);
			movie.setWatched(false);
			movie.setLiked(false);
		} else {
			movie.setFuture(true);
		}
		this.moviesRepository.save(movie);
		sendToNetflix(movieId, userId, URL_NETFLIX_MOVIE_WATCHED);
	}

	public List<MyMovies> getMyFutureWatchedMovies(Long userId) {
		return this.moviesRepository.findByUserIdAndFuture(userId, Boolean.TRUE);
	}
	
	public List<MyMovies> getMyWatchedMovies(Long userId) {
		return this.moviesRepository.findByUserIdAndWatched(userId, Boolean.TRUE);
	}

	public List<MyMovies> getMyLikedMovies(Long userId) {
		return this.moviesRepository.findByUserIdAndLiked(userId, Boolean.TRUE);
	}

	public void setMovieAsLiked(Long userId, Long movieId) {
		MyMovies movie = this.moviesRepository.findByUserIdAndMovieId(userId, movieId);
		if (movie == null) {
			movie = new MyMovies();
			movie.setMovieId(movieId);
			movie.setUserId(userId);
			movie.setFuture(false);
			movie.setWatched(false);
			movie.setLiked(true);
		} else {
			movie.setLiked(true);
		}
		this.moviesRepository.save(movie);
		sendToNetflix(movieId, userId, URL_NETFLIX_MOVIE_LIKED);
	}
	
	private void sendToNetflix (Long movieId, Long userId, String url) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("movie_id", movieId.toString());
		params.put("user_id", userId.toString());
		HttpEntity<String> request = new HttpEntity<>(new String());
		ResponseEntity<String> response = this.loadBalanced
		  .exchange(url, HttpMethod.PUT, request, String.class, params);
		System.out.println(response.getBody().toString());
	}

}
