package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.exception.CartEmptyException;
import com.example.ecommerce.service.AddToCartService;

@RestController
@RequestMapping("/cartItem")
public class AddToCartController {
	@Autowired
	AddToCartService addToCartService;

	@PostMapping
	public ResponseEntity<AddToCart> addProductItem(@RequestParam Long productId, @RequestParam double quantity,
			@RequestParam long userId) throws CartEmptyException {
		AddToCart addToCart = addToCartService.addProductItem(productId, quantity, userId);
		return new ResponseEntity<AddToCart>(addToCart, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<AddToCart>> getAllItems() {
		return new ResponseEntity<List<AddToCart>>(addToCartService.getAllItems(), HttpStatus.OK);
	}

}
