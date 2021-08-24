package com.example.ecommerce.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.CartEmptyException;
import com.example.ecommerce.exception.CartIdDoesntExistsException;
import com.example.ecommerce.repository.AddToCartRepository;
import com.example.ecommerce.repository.OrderDetailsRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;

@Service
public class AddToCartServiceImpl implements AddToCartService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddToCartRepository addToCartRepository;
	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Override
	public AddToCart addProductItem(Long productId, double quantity, long userId) throws CartEmptyException {
		AddToCart addToCart = new AddToCart();
		Product product = productRepository.findByProductId(productId);

		User user = userRepository.findByUserId(userId);
		if (product != null && user != null) {
			addToCart.setProduct(product);
			addToCart.setUser(user);
			addToCart.setDate(new Date());
			addToCart.setQuantity(quantity);
			addToCart.setAmount(quantity * product.getCost());
			addToCart.setProductStatus("Not Purchased");
			return addToCartRepository.save(addToCart);
		} else {
			throw new CartEmptyException(" your Cart is Empty !! checkOut");
		}
	}

	@Override
	public List<AddToCart> getAllItems() {

		return addToCartRepository.findAll();
	}

}
