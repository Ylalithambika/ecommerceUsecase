package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.exception.CartIdDoesntExistsException;
import com.example.ecommerce.service.OrderDetailsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderDetailsController {
	@Autowired
	OrderDetailsService orderDetailsService;

	@PostMapping
	public String purchaseProduct(@RequestParam long cartId) throws CartIdDoesntExistsException {
		return orderDetailsService.purchaseProduct(cartId);

	}
}
