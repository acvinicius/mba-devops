package com.fiap.microservices.register.model;

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
@Table(name = "myMovies")
public class MyMovies implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5072500446137394457L;

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	@Column(name = "mymovies_id", nullable = false, unique = true)
	private Long id;
	@Column(name = "movies_id", nullable = false, unique = true)
	private Long movieId;
	@Column(name = "user_id", nullable = false, unique = true)
	private Long userId;
	@Column(name = "liked", nullable = true)
	private boolean liked;
	@Column(name = "watched", nullable = true)
	private boolean watched;
	@Column(name = "future", nullable = true)
	private boolean future;
	
}
