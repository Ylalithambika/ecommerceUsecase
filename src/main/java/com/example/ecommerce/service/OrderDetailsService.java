package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.exception.CartIdDoesntExistsException;

public interface OrderDetailsService {
	String purchaseProduct(long cartId) throws CartIdDoesntExistsException;
}
