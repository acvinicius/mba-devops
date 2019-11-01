package com.fiap.microservices.register.controller;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
		user.setMyFutureMovies(new ArrayList<MyMovies>());
		user.setMyWatchedMovies(new ArrayList<MyMovies>());
		return this.service.save(user);
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public User find (@RequestParam("user_id") Long userId) {
		return this.service.find(userId);
	}
	
	
}
