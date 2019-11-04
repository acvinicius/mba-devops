package com.fiap.microservices.netflix.servicedesk.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.microservices.netflix.servicedesk.enums.Status;
import com.fiap.microservices.netflix.servicedesk.model.OrderDTO;
import com.fiap.microservices.netflix.servicedesk.service.ServiceDeskService;


@RestController
@RequestMapping(value="/v1")
public class ServiceDeskController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1499412735506460046L;

	private static final Logger logger = LoggerFactory.getLogger(ServiceDeskController.class);

	@Autowired
	private ServiceDeskService service;
	
	@RequestMapping(value="/servicedesk", method = RequestMethod.POST)
	public Long createOrder(@RequestParam ("message") String message, @RequestParam ("user_id") Long userId,
			@RequestParam ("movie_id") Long movieId) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage(message);
		orderDTO.setStatus(Status.ABERTO);
		orderDTO.setUserId(userId);
		orderDTO.setMovieId(movieId);
		return this.service.createOrder(orderDTO);
	}
	
	@RequestMapping(value="/servicedesk/update", method = RequestMethod.POST)
	public void updateOrder(@RequestParam ("message") String message, @RequestParam ("order_id") Long orderId,
			@RequestParam ("status") String status) {
		this.service.updateOrder(message, orderId, status);
	}
	
	@RequestMapping(value="/servicedesk", method = RequestMethod.DELETE)
	public void delete(@RequestParam  ("id") Long id) {
		this.service.delete(id);
	}
	
	@RequestMapping(value="/servicedesk/all", method = RequestMethod.GET)
	public Iterable<OrderDTO> findAll() {
		return this.service.findAllOrders();
	}
	
	@RequestMapping(value="/servicedesk", method = RequestMethod.GET)
	public Iterable<OrderDTO> findAllByUser(@RequestParam ("user_id") Long userId) {
		return this.service.findAllOrdersByUser(userId);
	}
	
}
