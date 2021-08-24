package com.example.ecommerce.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(InvalidCredentailsException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(InvalidCredentailsException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatuscode(401);
		errorResponse.setDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException ex) {

		ValidationsErrorResponse errorResponse = new ValidationsErrorResponse();
		errorResponse.setMessage("Input Data is Invalid");
		errorResponse.setDateTime(LocalDateTime.now());
		errorResponse.setStatuscode(400);

		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		errors.forEach(error -> {
			errorResponse.getErrorsMap().put(error.getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(UserAlreadyExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatuscode(402);
		errorResponse.setDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.ALREADY_REPORTED);
	}

	@ExceptionHandler(PasswordNotMatchingException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(PasswordNotMatchingException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatuscode(403);
		errorResponse.setDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(CartEmptyException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(CartEmptyException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatuscode(404);
		errorResponse.setDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CartIdDoesntExistsException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(CartIdDoesntExistsException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatuscode(405);
		errorResponse.setDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
