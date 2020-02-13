package com.shop.rest.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class UserExistsAdvice {

	@ResponseBody
	@ExceptionHandler(UserExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT )
	String UserExistHandler(UserExistsException ex) {
		return ex.getMessage();
	}
}
