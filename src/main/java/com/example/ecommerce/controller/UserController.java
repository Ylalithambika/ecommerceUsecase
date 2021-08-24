package com.example.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.dto.UserRequestDto;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.InvalidCredentailsException;
import com.example.ecommerce.exception.PasswordNotMatchingException;
import com.example.ecommerce.exception.UserAlreadyExistsException;
import com.example.ecommerce.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping
	public String saveUser(@RequestBody @Valid UserRequestDto userRequestDto)
			throws UserAlreadyExistsException, PasswordNotMatchingException {
		String user = userServiceImpl.saveUser(userRequestDto);
		return user;
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody UserCredentailsDto userCredentailsDto)
			throws InvalidCredentailsException

	{
		return userServiceImpl.login(userCredentailsDto);
	}

	@GetMapping("/statement")
	public ResponseEntity<List<OrderDetails>> getOrders(@RequestParam long userId,
			@Valid @RequestParam @Min(1) @Max(12) int month, @Valid @RequestParam @Min(2021) @Max(3000) int year) {
		return new ResponseEntity<List<OrderDetails>>(userServiceImpl.getOrders(userId, month, year), HttpStatus.OK);
	}
}
