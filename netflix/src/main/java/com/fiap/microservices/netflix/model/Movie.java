package com.fiap.microservices.netflix.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3984776100742212972L;

	private Integer id;
	private String name;
	private String details;
	private String genero;
	private boolean hasProblem;
	private Integer countWatched;
	private Integer countLiked;
	private Integer countDisliked;
	
}
