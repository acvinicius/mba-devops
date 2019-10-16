package com.fiap.microservices.netflix.servicedesk.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.fiap.microservices.netflix.servicedesk.model.OrderDTO;
import com.fiap.microservices.netflix.servicedesk.repository.ServiceDeskRepository;

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

}