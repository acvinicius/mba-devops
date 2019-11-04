package com.fiap.microservices.netflix.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3984776100742212972L;

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	@Column(name = "movie_id", nullable = false, unique = true)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "details", nullable = false)
	private String details;
	@Column(name = "type", nullable = false)
	private String type;
	@Column(name = "hasProblem", nullable = false)
	private boolean hasProblem;
	@Column(name = "countWatched", nullable = false)
	private Long countWatched;
	@Column(name = "countLiked", nullable = false)
	private Long countLiked;
	
}
