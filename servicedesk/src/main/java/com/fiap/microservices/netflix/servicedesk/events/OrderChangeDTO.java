package com.fiap.microservices.netflix.servicedesk.events;

import java.io.Serializable;

import com.fiap.microservices.netflix.servicedesk.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderChangeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026861208942280698L;

	private Long id;
	private Long userId;
	private Status status;

	public OrderChangeDTO(Long id, Long userId, Status status) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
	}
	
}
