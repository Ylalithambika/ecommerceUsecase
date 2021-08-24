package com.example.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.CartIdDoesntExistsException;
import com.example.ecommerce.service.OrderDetailsService;

@ExtendWith(MockitoExtension.class)
public class OrderDetailsControllerTest {
	@Mock
	OrderDetailsService orderDetailsService;
	@InjectMocks
	OrderDetailsController orderDetailsController;
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
	@DisplayName("purchaseProduct: positive Scenario")
	public void purchaseProductTest() throws CartIdDoesntExistsException {
		when(orderDetailsService.purchaseProduct(1L)).thenReturn("Order Placed");
		String result = orderDetailsController.purchaseProduct(1L);
		verify(orderDetailsService).purchaseProduct(1L);
		assertEquals("Order Placed", result);
	}

	@Test
	@DisplayName("purchase Product  negative")
	public void purchaseProductTest1() throws CartIdDoesntExistsException {
		when(orderDetailsService.purchaseProduct(1L)).thenThrow(CartIdDoesntExistsException.class);
		assertThrows(CartIdDoesntExistsException.class, () -> orderDetailsController.purchaseProduct(1L));
	}

}