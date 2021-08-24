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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.dto.UserRequestDto;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.InvalidCredentailsException;
import com.example.ecommerce.exception.PasswordNotMatchingException;
import com.example.ecommerce.exception.UserAlreadyExistsException;
import com.example.ecommerce.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@Mock
	UserServiceImpl userServiceImpl;

	@InjectMocks
	UserController userController;
	static UserCredentailsDto userCredentailsDto;
	static UserRequestDto userRequestDto;
	static User user;
	static Address address;
	static OrderDetails orderDetails;
	static Product product;

	@BeforeAll
	public static void setUp() {
		address = new Address();
		address.setCity("Chittoor");
		address.setDoorNo("19-286");
		address.setPincode(517001);
		address.setState("AP");

		userRequestDto = new UserRequestDto();
		userRequestDto.setUserName("lalitha");
		userRequestDto.setEmailId("lalitha@gmailcom");
		userRequestDto.setPassword("Lalitha@1412");
		userRequestDto.setConfrimPassword("Lalitha@1412");
		userRequestDto.setGender("female");
		userRequestDto.setMobileNo("7013736967");
		userRequestDto.setAddress(address);

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
		product = new Product();
		product.setCategory("laptops");
		product.setCost(31000);
		product.setProductDescription("BEST");
		product.setProductId(1L);
		product.setProductName("HP");

	}

	@Test
	@DisplayName("User registered Sucessfully")
	public void saveUserTest() throws UserAlreadyExistsException, PasswordNotMatchingException {
		when(userServiceImpl.saveUser(userRequestDto)).thenReturn("Authenticated!!User saved successfully");
		String result = userController.saveUser(userRequestDto);
		verify(userServiceImpl).saveUser(userRequestDto);
		assertEquals("Authenticated!!User saved successfully", result);
	}

	@Test
	@DisplayName("login:Postive Scenario")
	public void loginTest1() {
		when(userServiceImpl.login(userCredentailsDto))
				.thenReturn(new ResponseEntity<>("Login success", HttpStatus.OK));
		ResponseEntity<String> result = userController.login(userCredentailsDto);
		verify(userServiceImpl).login(userCredentailsDto);
		assertEquals("Login success", result.getBody());

	}

	@Test
	@DisplayName("login :Negative scenario")
	public void loginTest2() {
		when(userServiceImpl.login(userCredentailsDto)).thenThrow(InvalidCredentailsException.class);
		assertThrows(InvalidCredentailsException.class, () -> userController.login(userCredentailsDto));

	}

	@Test
	public void getOrders() {
		List<OrderDetails> order = new ArrayList<>();
		order.add(orderDetails);
		when(userServiceImpl.getOrders(1L, 8, 2021)).thenReturn(order);
		ResponseEntity<List<OrderDetails>> result = userController.getOrders(1L, 8, 2021);
		verify(userServiceImpl).getOrders(1L, 8, 2021);
		assertEquals(order, result.getBody());
	}

}