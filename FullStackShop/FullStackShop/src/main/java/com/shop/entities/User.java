package com.shop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "EMAIL")
	private String email;

	public String getErrorMessage() {
		return null;
	}

	public User() {
	}

	public User(String name, String address, String email) {
		this.name = name;
		this.address = address;
		this.email = email;
	}
}