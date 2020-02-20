package com.shop.auth.model;

/** 
 * User authentication
 * Define JPA and Hibernate Entity
 * 
 */


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.springframework.data.annotation.Transient;

import java.util.Set;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS_AUTH")
public class User {
	@Id
	@GeneratedValue//(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;

	@Transient
	private String passwordConfirm;

	@ManyToMany
	@JoinTable(
			  name = "USERS_AUTH_ROLES", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<UserRoles> roles;
	
	private String enabled;
	
}