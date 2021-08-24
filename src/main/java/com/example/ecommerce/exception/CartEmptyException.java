package com.example.ecommerce.exception;

public class CartEmptyException extends Exception {
	String message;

	public CartEmptyException(String message) {
		super(message);
		this.message = message;
	}

	public CartEmptyException() {
		super();
	}

}
