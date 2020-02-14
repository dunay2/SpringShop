package com.shop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_SECURITY")
public class UserSecurity {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	@Column(name = "NAME")
	@NotEmpty
	private String username;
	@Column(name = "PASSWORD")
	@NotEmpty
	private String password;
	@Column(name = "ENABLED")
	private boolean isEnabled;
	@Column(name = "ROLE")
	private String role;
}