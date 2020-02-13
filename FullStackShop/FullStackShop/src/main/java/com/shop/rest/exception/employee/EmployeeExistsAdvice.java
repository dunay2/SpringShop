package com.shop.rest.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EmployeeExistsAdvice {

	@ResponseBody
	@ExceptionHandler(EmployeeExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT )
	String EmployeeExitsHandler(EmployeeExistsException ex) {
		return ex.getMessage();
	}
}
