package com.fiap.microservices.register.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.microservices.register.model.User;
import com.fiap.microservices.register.repository.UsersRepository;

@Service
public class RegisterService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8808680048770720188L;

	@Autowired
	private UsersRepository repository;
	
	public Long save(User user) {
		user = this.repository.save(user);
		return user.getId();
	}

	public User find(Long userId) {
		return this.repository.findById(userId);
	}

	
	
}
