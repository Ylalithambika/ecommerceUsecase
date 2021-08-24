package com.example.ecommerce.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.dto.UserRequestDto;
import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.CartIdDoesntExistsException;
import com.example.ecommerce.exception.InvalidCredentailsException;
import com.example.ecommerce.repository.AddToCartRepository;
import com.example.ecommerce.repository.OrderDetailsRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	@Autowired
	AddToCartRepository addToCartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;

	@Override
	public String purchaseProduct(long cartId) throws CartIdDoesntExistsException {

		AddToCart addToCart = addToCartRepository.findByCartId(cartId);
		if (addToCart != null) {

			OrderDetails order = new OrderDetails();
			order.setDate(new Date());
			//order.setProductName(addToCart.getProduct().getProductName());
			order.setProduct(addToCart.getProduct());
			order.setUser(addToCart.getUser());
			order.setAmount(addToCart.getAmount());
			order.setOrderStatus("Purchased");
			order.setCartId(cartId);
			orderDetailsRepository.save(order);
			return "Order Placed";

		} else {
			throw new CartIdDoesntExistsException("CartItem Doesnt Exists");
		}

	}
}
