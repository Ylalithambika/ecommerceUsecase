package com.example.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.UserCredentailsDto;
import com.example.ecommerce.dto.UserRequestDto;
import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.InvalidCredentailsException;
import com.example.ecommerce.exception.PasswordNotMatchingException;
import com.example.ecommerce.exception.UserAlreadyExistsException;
import com.example.ecommerce.repository.UserRepository;

@Service
public class UserServiceImpl {
	@Autowired
	UserRepository userRepository;

	
	public String saveUser(UserRequestDto userRequestDto)
			throws UserAlreadyExistsException, PasswordNotMatchingException {
		User user = new User();

		BeanUtils.copyProperties(userRequestDto, user);
		User user2 = userRepository.findByUserName(userRequestDto.getUserName());
		if (user2 != null) {
			throw new UserAlreadyExistsException("user already exists");
		}
		if ((userRequestDto.getPassword().equals(userRequestDto.getConfrimPassword()))) {

			User user1 = userRepository.save(user);
			return "Authenticated !! user saved Successfullly";
		}
		throw new PasswordNotMatchingException("please enter valid password");
	}

	public ResponseEntity<String> login(@Valid UserCredentailsDto userCredentailsDto) {

		User user = userRepository.findByUserNameAndPassword(userCredentailsDto.getUserName(),
				userCredentailsDto.getPassword());
		if (user != null)
			return new ResponseEntity<>("Login success", HttpStatus.OK);

		throw new InvalidCredentailsException("Invalid Credentials..!!Please Verify your Credentials and Try Again.");
	}

	public List<OrderDetails> getOrders(long userId, @Valid @Min(1) @Max(12) int month,
			@Valid @Min(2021) @Max(3000) int year) {

		return userRepository.getOrders(userId, month, year);
	}
}
