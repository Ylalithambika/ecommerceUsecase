package com.example.ecommerce.exception;

public class UserAlreadyExistsException extends Exception {
	String message;

	public UserAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public UserAlreadyExistsException() {
		super();
	}
}
