package com.fiap.microservices.register.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fiap.microservices.register.model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {

	<S extends User> S save(User user);
	
	List<User> findByUsername(String username);
	
	User findById(Long id);
	
}