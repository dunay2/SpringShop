package com.shop.app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public
class EmployeeDTO {

	private @Id @GeneratedValue Long id;
	private String name;
	private String role;

	EmployeeDTO() {}

	public EmployeeDTO(String name, String role) {
		this.name = name;
		this.role = role;
	}
}
