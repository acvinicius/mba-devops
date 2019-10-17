package com.fiap.microservices.netflix.servicedesk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fiap.microservices.netflix.servicedesk.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583635853935385036L;

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	@Column(name = "order_id", nullable = false, unique = true)
	private Long id;
	@Column(name = "user_id", nullable = false)
	private Long userId;
	@Column(name = "message", nullable = false)
	private String message;
	@Column(name = "movie_id", nullable = true)
	private Long movieId;	
	@Column(name = "status", nullable = true)
	private Status status;
	
}
