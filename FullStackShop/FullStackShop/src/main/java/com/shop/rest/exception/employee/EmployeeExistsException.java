package com.shop.rest.exception.employee;

public class EmployeeExistsException extends RuntimeException {

	public EmployeeExistsException(String name) {
		super("Employee" + name + "already exists");
	}
}
