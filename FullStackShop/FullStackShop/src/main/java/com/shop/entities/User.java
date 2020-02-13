package com.shop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	@NotEmpty
	@Length(max = 50)
	@Column(name = "NAME")
	private String name;
	@NotEmpty
	@Length(max = 150)
	@Column(name = "ADDRESS")
	private String address;
	@Email
	@NotEmpty
	@Length(max = 80)
	@Column(name = "EMAIL")
	private String email;

	
	
	

	public User() {
	}

	public User(String name, String address, String email) {
		this.name = name;
		this.address = address;
		this.email = email;
	}
}