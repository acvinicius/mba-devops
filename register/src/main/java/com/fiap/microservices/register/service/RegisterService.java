package com.fiap.microservices.register.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fiap.microservices.register.config.ConfigProperties;
import com.fiap.microservices.register.config.GeneralConfig;
import com.fiap.microservices.register.enums.MovieStatus;
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
	private ConfigProperties properties;
	
	@Autowired
	private GeneralConfig config;
	
	public Long save(User user) {
		user = this.repository.save(user);
		return user.getId();
	}

	public User find(Long userId) {
		return this.repository.findById(userId);
	}

	public void markAsWatched(Long userId, Long movieId) {
		User user = new User();
		user = this.find(userId);
		if (user != null) {
			MyMovies movies = new MyMovies();
			movies.setUserId(userId);
			movies.setMovieId(movieId);
			movies.setLiked(false);
			movies.setMovieStatus(MovieStatus.WATCHED);
			this.moviesRepository.save(movies);
		}
	}

	public void setMyFutureWatchedMovies(Long userId, Long movieId) {
		User user = new User();
		user = this.find(userId);
		if (user != null) {
			MyMovies movies = new MyMovies();
			movies.setUserId(userId);
			movies.setMovieId(movieId);
			movies.setLiked(false);
			movies.setMovieStatus(MovieStatus.FUTURE);
			this.moviesRepository.save(movies);
		}
		
	}

	public List<MyMovies> getMyFutureWatchedMovies(Long userId) {
		User user = new User();
		user = this.find(userId);
		return user.getMyFutureMovies();
	}
	
	public List<MyMovies> getMyWatchedMovies(Long userId) {
		User user = new User();
		user = this.find(userId);
		return user.getMyWatchedMovies();
	}

	public List<MyMovies> getMyLikedMovies(Long userId) {
		List<MyMovies> myLikedMovies = new ArrayList<MyMovies>();
		myLikedMovies = this.moviesRepository.findByUserId(userId);
		for (MyMovies movie : myLikedMovies) {
			if (!movie.isLiked())
				myLikedMovies.remove(movie);
		}
		return myLikedMovies;
	}

	public void setMovieAsLiked(Long userId, Long movieId) {
		User user = new User();
		user = this.find(userId);
		if (user != null) {
			MyMovies movies = new MyMovies();
			movies.setUserId(userId);
			movies.setMovieId(movieId);
			movies.setLiked(true);
			movies.setMovieStatus(MovieStatus.WATCHED);
			user.getMyWatchedMovies().add(movies);
			this.repository.save(user);
		}
		StringBuilder params = new StringBuilder();
		params.append("movie_id=").append(movieId)
		.append("&user_id").append(userId);
		String url = properties.getUrlLikedMovie().concat(params.toString());
		RestTemplate restTemplate = config.restTemplate();
		HttpEntity<MyMovies> request = new HttpEntity<>(new MyMovies());
		ResponseEntity<String> response = restTemplate
		  .exchange(url, HttpMethod.PATCH, request, String.class);
		 System.out.println(response.getBody().toString());
	}

}
