package com.shop.app.model;

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
@Table(name = "UserDTO")
public class UserDTO {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	@NotEmpty(message = "error.name.empty")
	@Length(max = 50, message = "error.name.length")
	@Column(name = "NAME")
	private String name;
	@NotEmpty(message = "error.address.empty")
	@Length(max = 150, message = "error.address.length")
	@Column(name = "ADDRESS")
	private String address;
	@Email(message = "error.email.email")
	@NotEmpty(message = "error.email.empty")
	@Length(max = 80, message = "error.email.length")
	@Column(name = "EMAIL")
	private String email;

	public UserDTO() {
	}

	public UserDTO(String name, String address, String email) {
		this.name = name;
		this.address = address;
		this.email = email;
	}
}