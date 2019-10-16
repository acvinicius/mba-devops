package com.fiap.microservices.netflix.servicedesk.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fiap.microservices.netflix.servicedesk.model.OrderDTO;

public interface ServiceDeskRepository extends CrudRepository<OrderDTO, Long> {

	<S extends OrderDTO> S save(OrderDTO orderDTO);
	
	Optional<OrderDTO> findById(Long id);
	
}
