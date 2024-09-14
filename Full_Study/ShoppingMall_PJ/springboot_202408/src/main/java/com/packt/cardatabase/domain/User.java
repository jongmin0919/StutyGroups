package com.packt.cardatabase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private Long id;

	@Column(nullable = false, unique = true)
	private String userid;

	@Column(nullable=false)
	private String username;
	 
	@Column(nullable=false)
	private String password;

	@Column(nullable=false)
	private String role;

}
