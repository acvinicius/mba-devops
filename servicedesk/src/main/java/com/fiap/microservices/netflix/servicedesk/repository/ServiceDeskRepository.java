package com.fiap.microservices.netflix.servicedesk.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fiap.microservices.netflix.servicedesk.model.OrderDTO;

@Repository
public interface ServiceDeskRepository extends CrudRepository<OrderDTO, Long> {

	<S extends OrderDTO> S save(OrderDTO orderDTO);
	
	Optional<OrderDTO> findById(Long id);
	
	void deleteById(Long id) ;
	
	Iterable<OrderDTO> findAll();
	
	Iterable<OrderDTO> findAllByUserId(Long userId);
	
}
