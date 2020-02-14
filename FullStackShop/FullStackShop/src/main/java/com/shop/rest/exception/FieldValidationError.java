package com.shop.rest.exception;

import java.awt.TrayIcon.MessageType;

import lombok.Data;

@Data
public class FieldValidationError {
	private String field;
	private String message;
	private MessageType type;

}