package com.fiap.microservices.netflix.servicedesk.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.microservices.netflix.servicedesk.model.OrderDTO;
import com.fiap.microservices.netflix.servicedesk.service.ServiceDeskService;


@RestController
@RequestMapping(value="/v1")
public class ServiceDeskController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1499412735506460046L;

	@Autowired
	private ServiceDeskService service;
	
	@RequestMapping(value="/servicedesk", method = RequestMethod.POST)
	public Long createOrder(@RequestBody OrderDTO orderDTO) {
		return service.createOrder(orderDTO);
	}
}
