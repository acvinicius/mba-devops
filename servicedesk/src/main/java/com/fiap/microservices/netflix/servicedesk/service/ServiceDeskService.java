package com.fiap.microservices.netflix.servicedesk.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.microservices.netflix.servicedesk.events.SimpleSourceBean;
import com.fiap.microservices.netflix.servicedesk.model.OrderDTO;
import com.fiap.microservices.netflix.servicedesk.repository.ServiceDeskRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class ServiceDeskService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1996575370494470450L;

	@Autowired
	private ServiceDeskRepository repository;
	
	@Autowired
	private SimpleSourceBean simpleSourceBean;
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceDeskService.class);
	
	public Long createOrder(OrderDTO orderDTO) {
		orderDTO = this.repository.save(orderDTO);
		return orderDTO.getId();
	}
	
	public void updateOrder(OrderDTO orderDTO) {
		repository.save(orderDTO);
		simpleSourceBean.publishOrgChange(orderDTO.getStatus(), orderDTO.getId(), orderDTO.getUserId());
	} 
	
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@HystrixCommand(fallbackMethod = "fallbackOrders", threadPoolKey = "ordersThreadPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "30"),
			@HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
					@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
	public Iterable<OrderDTO> findAllOrders() {
		return this.repository.findAll();
	}

	public Iterable<OrderDTO> fallbackOrders(Throwable hystrixCommand) {
		List<OrderDTO> dtos = new ArrayList<OrderDTO>();
		OrderDTO dto = new OrderDTO();
		dtos.add(dto);
		return dtos;
	}

	public Iterable<OrderDTO> findAllOrdersByUser(Long userId) {
		return this.repository.findAllByUserId(userId);
	}

}
