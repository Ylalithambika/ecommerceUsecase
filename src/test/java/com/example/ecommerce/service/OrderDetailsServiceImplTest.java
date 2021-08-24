package com.example.ecommerce.service;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.CartIdDoesntExistsException;
import com.example.ecommerce.repository.AddToCartRepository;
import com.example.ecommerce.repository.OrderDetailsRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderDetailsServiceImplTest {
	@Mock
	OrderDetailsRepository orderDetailsRepository;
	@Mock
	AddToCartRepository addToCartRepository;
	@Mock
	UserRepository userRepository;
	@InjectMocks
	OrderDetailsServiceImpl orderDetailsServiceImpl;
	static AddToCart addToCart;
	static Product product;
	static User user;
	static Address address;
	static UserCredentailsDto userCredentailsDto;
	static OrderDetails orderDetails;

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

		orderDetails = new OrderDetails();
		orderDetails.setDate(new Date());
		orderDetails.setOrderId(1L);
		orderDetails.setOrderStatus("Purchased");
		orderDetails.setProduct(product);
		orderDetails.setUser(user);
		orderDetails.setAmount(5000.00);
		orderDetails.setCartId(1L);

		userCredentailsDto = new UserCredentailsDto();
		userCredentailsDto.setPassword("Lalitha@1412");
		userCredentailsDto.setUserName("Lalitha");

	}

	@Test
	@DisplayName("OrderDetails : positive scenario")
	public void purchaseProduct() throws CartIdDoesntExistsException {
		when(addToCartRepository.findByCartId(1L)).thenReturn(addToCart);
		when(orderDetailsRepository.save(any(OrderDetails.class))).thenReturn(orderDetails);
		String result = orderDetailsServiceImpl.purchaseProduct(1L);
		assertEquals("Order Placed", result);
	}

	@Test
	@DisplayName("OrderDetails :negative scenario")
	public void purchaseProduct1() {
		when(addToCartRepository.findByCartId(1L)).thenReturn(null);
		assertThrows(CartIdDoesntExistsException.class, () -> orderDetailsServiceImpl.purchaseProduct(1L));
	}
}
