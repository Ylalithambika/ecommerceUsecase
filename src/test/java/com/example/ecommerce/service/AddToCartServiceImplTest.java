package com.example.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.CartEmptyException;
import com.example.ecommerce.repository.AddToCartRepository;
import com.example.ecommerce.repository.OrderDetailsRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AddToCartServiceImplTest {
	@Mock
	ProductRepository productRepository;
	@Mock
	UserRepository userRepository;
	@Mock
	AddToCartRepository addToCartRepository;
	@Mock
	OrderDetailsRepository orderDetailsRepository;

	@InjectMocks
	AddToCartServiceImpl addToCartServiceImpl;
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
	@DisplayName("addtocart : postive scenario")
	public void addProductItemTest() throws CartEmptyException {
		when(productRepository.findByProductId(1L)).thenReturn(product);
		when(userRepository.findByUserId(1L)).thenReturn(user);
		when(addToCartRepository.save(any(AddToCart.class))).thenReturn(addToCart);
		AddToCart result = addToCartServiceImpl.addProductItem(1L, 2d, 1L);
		assertEquals(addToCart, result);
	}

	@Test
	@DisplayName("addtocart : negative scenario")
	public void addProductItemTest1() throws CartEmptyException {
		when(productRepository.findByProductId(1L)).thenReturn(null);
		when(userRepository.findByUserId(1L)).thenReturn(null);

		assertThrows(CartEmptyException.class, () -> addToCartServiceImpl.addProductItem(1L, 2d, 1L));
	}

	@Test
	public void getCartItemsTest()

	{
		List<AddToCart> addToCarts = new ArrayList<>();
		addToCarts.add(addToCart);
		when(addToCartRepository.findAll()).thenReturn(addToCarts);
		List<AddToCart> result = addToCartServiceImpl.getAllItems();
		assertEquals(addToCarts, result);
	}
}
