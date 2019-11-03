package com.fiap.microservices.register.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fiap.microservices.register.enums.MovieStatus;

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
	@Column(name = "liked", nullable = true)
	private boolean liked;
	@Column(name = "user_id", nullable = false, unique = true)
	private Long userId;
	@Column(name = "movie_status", nullable = false)
	private MovieStatus movieStatus;
	
}
