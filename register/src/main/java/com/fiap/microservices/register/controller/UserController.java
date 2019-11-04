package com.fiap.microservices.register.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.microservices.register.model.MyMovies;
import com.fiap.microservices.register.model.User;
import com.fiap.microservices.register.service.RegisterService;

@RestController
@RequestMapping(value="/v1")
public class UserController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1028934313553588072L;
	
	@Autowired
	private RegisterService service;

	@RequestMapping(value="/register", method = RequestMethod.PUT)
	public Long save (@RequestParam("username") String username, @RequestParam("password") String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return this.service.save(user);
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public User find (@RequestParam("user_id") Long userId) {
		return this.service.find(userId);
	}
	
	@RequestMapping(value="/register/movies/liked", method = RequestMethod.GET)
	public List<MyMovies> getMyLikedMovies (@RequestParam("user_id") Long userId) {
		return this.service.getMyLikedMovies(userId);
	}
	
	@RequestMapping(value="/register/movies/liked", method = RequestMethod.PUT)
	public void setMovieAsLiked (@RequestParam("user_id") Long userId, @RequestParam("movie_id") Long movieId) {
		this.service.setMovieAsLiked(userId, movieId);
	}
	
	@RequestMapping(value="/register/movies/watched", method = RequestMethod.GET)
	public List<MyMovies> getMyWatchedMovies (@RequestParam("user_id") Long userId) {
		return this.service.getMyWatchedMovies(userId);
	}
	
	@RequestMapping(value="/register/movies/watched", method = RequestMethod.PUT)
	public void markAsWatched (@RequestParam("user_id") Long userId, @RequestParam("movie_id") Long movieId) {
		this.service.markAsWatched(userId, movieId);
	}

	@RequestMapping(value="/register/movies/future", method = RequestMethod.GET)
	public List<MyMovies> getMyFutureWatchedMovies (@RequestParam("user_id") Long userId) {
		return this.service.getMyFutureWatchedMovies(userId);
	}

	@RequestMapping(value="/register/movies/future", method = RequestMethod.PUT)
	public void markMyFutureWatchedMovies (@RequestParam("user_id") Long userId, @RequestParam("movie_id") Long movieId) {
		this.service.setMyFutureWatchedMovies(userId, movieId);
	}
	
}
