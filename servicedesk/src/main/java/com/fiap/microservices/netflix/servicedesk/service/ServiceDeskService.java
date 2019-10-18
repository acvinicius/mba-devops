package com.fiap.microservices.netflix.servicedesk.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.microservices.netflix.servicedesk.model.OrderDTO;
import com.fiap.microservices.netflix.servicedesk.repository.ServiceDeskRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ServiceDeskService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1996575370494470450L;

	@Autowired
	private ServiceDeskRepository repository;
	
	public Long createOrder(OrderDTO orderDTO) {
		orderDTO = this.repository.save(orderDTO);
		return orderDTO.getId();
	}
	
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@HystrixCommand(fallbackMethod = "fallbackOrders")
	public Iterable<OrderDTO> findAllOrders() {
		return this.repository.findAll();
	}

	public Iterable<OrderDTO> fallbackOrders(Throwable hystrixCommand) {
		List<OrderDTO> dtos = new ArrayList<OrderDTO>();
		OrderDTO dto = new OrderDTO();
		dtos.add(dto);
		return dtos;
	}

}
