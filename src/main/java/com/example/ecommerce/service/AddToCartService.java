package com.example.ecommerce.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.exception.CartEmptyException;

public interface AddToCartService {

	AddToCart addProductItem(Long productId, double quantity, long userId) throws CartEmptyException;

	List<AddToCart> getAllItems();

}
