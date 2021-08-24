package com.example.ecommerce.exception;

public class CartIdDoesntExistsException extends Exception {
	String message;

	public CartIdDoesntExistsException(String message) {
		super(message);
		this.message = message;
	}

	public CartIdDoesntExistsException() {
		super();
	}

}
