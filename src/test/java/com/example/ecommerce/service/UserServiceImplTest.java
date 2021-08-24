package com.example.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.dto.UserRequestDto;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.InvalidCredentailsException;
import com.example.ecommerce.exception.PasswordNotMatchingException;
import com.example.ecommerce.exception.UserAlreadyExistsException;
import com.example.ecommerce.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserServiceImpl userServiceImpl;

	static UserRequestDto userRequestDto;
	static User user;
	static Address address;
	static OrderDetails orderDetails;
	static UserRequestDto userRequestDto1;
	static User user1;
	static UserRequestDto userRequestDto2;
	static UserCredentailsDto userCredentailsDto;

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

		userRequestDto1 = new UserRequestDto();
		userRequestDto1.setUserName("lalitha");
		userRequestDto1.setEmailId("lalitha@gmailcom");
		userRequestDto1.setPassword("Lalitha@1412");
		userRequestDto1.setConfrimPassword("Lalitha@");
		userRequestDto1.setGender("female");
		userRequestDto1.setMobileNo("7013736967");
		userRequestDto1.setAddress(address);

		user = new User();
		user.setUserName("lalitha");
		user.setUserId(1L);
		user.setPassword("Lalitha@1412");
		user.setConfrimPassword("Lalitha@1412");
		user.setMobileNo("7013736967");
		user.setEmailId("lalitha@gmail.com");
		user.setGender("female");
		user.setAddress(address);

//		orderDetails.setDate(new Date());
//		orderDetails.setOrderId(1L);
//		orderDetails.setOrderStatus("Purchased");
//		orderDetails.setProductId(1L);
//		orderDetails.setProductName("Hp");
//		orderDetails.setUserId(1L);
//		orderDetails.setAmount(5000.00);

		userCredentailsDto = new UserCredentailsDto();
		userCredentailsDto.setPassword("Lalitha@1412");
		userCredentailsDto.setUserName("Lalitha");

	}

	@Test
	@DisplayName("save user: postive scenario")
	public void saveUserTest() throws UserAlreadyExistsException, PasswordNotMatchingException {
		when(userRepository.findByUserName(userRequestDto.getUserName())).thenReturn(null);

		if ((userRequestDto.getPassword().equals(userRequestDto.getConfrimPassword()))) {

			when(userRepository.save(any(User.class))).thenReturn(user);
			String result = userServiceImpl.saveUser(userRequestDto);
			assertEquals("Authenticated !! user saved Successfullly", result);

		}
	}

	@Test
	@DisplayName("save user : negative scenario")
	public void saveUserTest1() throws UserAlreadyExistsException, PasswordNotMatchingException {

		when(userRepository.findByUserName(userRequestDto.getUserName())).thenReturn(null);
		if ((!userRequestDto1.getPassword().equals(userRequestDto1.getConfrimPassword()))) {
			assertThrows(PasswordNotMatchingException.class, () -> userServiceImpl.saveUser(userRequestDto1));
		}

	}

	@Test
	@DisplayName("save user :negative scenario for alreadyexists")
	public void saveUserTest2() {
		userRequestDto2 = new UserRequestDto();
		userRequestDto2.setUserName("lalitha");
		userRequestDto2.setEmailId("lalitha@gmailcom");
		userRequestDto2.setPassword("Lalitha@1412");
		userRequestDto2.setConfrimPassword("Lalitha@1412");
		userRequestDto2.setGender("female");
		userRequestDto2.setMobileNo("7013736967");
		userRequestDto2.setAddress(address);
		when(userRepository.findByUserName(userRequestDto2.getUserName())).thenReturn(user);

		assertThrows(UserAlreadyExistsException.class, () -> userServiceImpl.saveUser(userRequestDto1));

	}

	@Test
	@DisplayName(" Login: positive")
	public void login() throws UserAlreadyExistsException, PasswordNotMatchingException {

		when(userRepository.findByUserNameAndPassword(userCredentailsDto.getUserName(),
				userCredentailsDto.getPassword())).thenReturn(user);
		ResponseEntity<String> result = userServiceImpl.login(userCredentailsDto);
		assertEquals("Login success", result.getBody());

	}

	@Test
	@DisplayName(" Login: negative")
	public void login1() throws UserAlreadyExistsException, PasswordNotMatchingException {

		when(userRepository.findByUserNameAndPassword(userCredentailsDto.getUserName(),
				userCredentailsDto.getPassword())).thenReturn(null);
		assertThrows(InvalidCredentailsException.class, () -> userServiceImpl.login(userCredentailsDto));

	}

	@Test
	public void getOrders() {
		List<OrderDetails> order = new ArrayList<>();
		order.add(orderDetails);
		when(userRepository.getOrders(1L, 8, 2021)).thenReturn(order);
		List<OrderDetails> result = userServiceImpl.getOrders(1L, 8, 2021);
		assertEquals(order, result);
	}
}
