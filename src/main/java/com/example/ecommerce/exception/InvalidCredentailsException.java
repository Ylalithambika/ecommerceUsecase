package com.example.ecommerce.exception;

public class InvalidCredentailsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	String message;

	public InvalidCredentailsException(String message) {
		super(message);
		this.message = message;
	}

	public InvalidCredentailsException() {
		super();
	}

}
