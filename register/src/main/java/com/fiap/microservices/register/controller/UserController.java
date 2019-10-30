package com.fiap.microservices.register.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public Long save (@PathVariable("username") String username, @PathVariable("password") String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return this.service.save(user);
	}
	
	public User find (@PathVariable("user_id") Long userId) {
		return this.service.find(userId);
	}
	
	
}
