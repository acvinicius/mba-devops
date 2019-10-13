package com.fiap.microservices.netflix.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8965167201516621171L;

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	@Column(name = "user_id", nullable = false, unique = true)
	private Long id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@OneToMany( targetEntity=MyMovies.class )
	private List<MyMovies> myWatchedMovies;
	@OneToMany( targetEntity=MyMovies.class )
	private List<MyMovies> myFutureMovies;
	
}
