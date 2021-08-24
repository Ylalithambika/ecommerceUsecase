package com.example.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.CartEmptyException;
import com.example.ecommerce.service.AddToCartService;


@ExtendWith(MockitoExtension.class)
public class AddToCartControllerTest {
	@Mock
	AddToCartService addToCartService;
	@InjectMocks
	AddToCartController addToCartController;
	static AddToCart addToCart;
	static Product product;
	static User user;
	static Address address;

	@BeforeAll
	public static void setup() {
		addToCart = new AddToCart();
		addToCart.setUser(user);
		addToCart.setAmount(40000);
		addToCart.setCartId(1L);
		addToCart.setDate(new Date());
		addToCart.setProduct(product);
		addToCart.setProductStatus("Best");
		addToCart.setQuantity(2d);

		product = new Product();
		product.setProductId(1L);
		product.setProductName("HP");
		product.setProductDescription("Best");
		product.setCategory("laptops");
		product.setCost(35000);

		address = new Address();
		address.setCity("Chittoor");
		address.setDoorNo("19-286");
		address.setPincode(517001);
		address.setState("AP");

		user = new User();
		user.setUserName("lalitha");
		user.setUserId(1L);
		user.setPassword("Lalitha@1412");
		user.setConfrimPassword("Lalitha@1412");
		user.setMobileNo("7013736967");
		user.setEmailId("lalitha@gmail.com");
		user.setGender("female");
		user.setAddress(address);

	}

	@Test
	@DisplayName("Addtocart : postive scenario")
	public void addProductItemTest() throws CartEmptyException {
		when(addToCartService.addProductItem(1L, 2d, 1L)).thenReturn(addToCart);
		ResponseEntity<AddToCart> result = addToCartController.addProductItem(1L, 2d, 1L);
		verify(addToCartService).addProductItem(1L, 2d, 1L);
		assertEquals(addToCart, result.getBody());
	}

	@Test
	@DisplayName("AddToCart : negative scenario")
	public void addProductItemTest1() throws CartEmptyException {
		when(addToCartService.addProductItem(1L, 400d, 1)).thenThrow(CartEmptyException.class);
		assertThrows(CartEmptyException.class, () -> addToCartController.addProductItem(1L, 400d, 1));
	}

	@Test
	@DisplayName("all the product list")
	public void getAllItems() {
		List<AddToCart> items = new ArrayList<>();
		items.add(addToCart);
		when(addToCartService.getAllItems()).thenReturn(items);
		ResponseEntity<List<AddToCart>> result = addToCartController.getAllItems();
		verify(addToCartService).getAllItems();
		assertEquals(items, result.getBody());
	}
}
