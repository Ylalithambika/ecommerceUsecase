package com.example.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ecommerce.controller.ProductController;
import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
	@Mock
	ProductRepository productRepository;
	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductService productService;
	@InjectMocks
	ProductController productController;

	static ProductRequestDto productRequestDto;
	static Product product;

	@BeforeAll

	public static void setUp() {
		productRequestDto = new ProductRequestDto();
		productRequestDto.setCategory("laptops");
		productRequestDto.setCost(35000);
		productRequestDto.setProductDescription("Best");
		productRequestDto.setProductName("HP");

		product = new Product();
		product.setProductId(1L);
		product.setProductName("HP");
		product.setProductDescription("Best");
		product.setCategory("laptops");
		product.setCost(35000);

	}

	@Test
	@DisplayName("search : postive Scenario")
	public void searchProduct() {
		List<Product> products = new ArrayList<>();
		products.add(product);
		when(productRepository.findByProductNameContainsAndCategory("HP", "laptops")).thenReturn(products);
		List<Product> result = productServiceImpl.searchProduct("HP", "laptops");
		assertEquals(products, result);
	}
	
	@Test
	@DisplayName("search : negative Scenario")
	public void searchProductTest() {
		List<Product> products = new ArrayList<>();
		products.add(product);
		when(productRepository.findByProductNameContainsAndCategory("HP", "laptops")).thenReturn(null);
		List<Product> result = productServiceImpl.searchProduct("HP", "laptops");
		assertEquals(null, result);
	}

}
