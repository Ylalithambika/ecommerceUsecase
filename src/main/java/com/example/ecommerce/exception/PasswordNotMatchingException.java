package com.example.ecommerce.exception;

public class PasswordNotMatchingException extends Exception {
	String message;

	public PasswordNotMatchingException(String message) {
		super(message);
		this.message = message;
	}

	public PasswordNotMatchingException() {
		super();
	}
}