package com.shop.rest.exception.user;

public class UserExistsException extends RuntimeException {

	public UserExistsException(String name) {
		super("User" + name + "already exists");
	}
}
