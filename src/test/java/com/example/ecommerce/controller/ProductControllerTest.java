package com.example.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
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
	public void search() {
		List<Product> products = new ArrayList<>();
		products.add(product);
		when(productService.searchProduct("HP", "laptops")).thenReturn(products);
		ResponseEntity<List<Product>> result = productController.searchProduct("HP", "laptops");
		verify(productService).searchProduct("HP", "laptops");
		assertEquals(products, result.getBody());
	}

	@Test

	@DisplayName("Product Details")
	public void getProductsTest() {
		List<Product> p = new ArrayList<>();
		p.add(product);
		when(productService.getProducts()).thenReturn(p);
		ResponseEntity<List<Product>> result = productController.getProducts();
		verify(productService).getProducts();
		assertEquals(p, result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Product added successfully")
	public void saveEmployee() {

		when(productService.saveProduct(productRequestDto)).thenReturn("Product added");
		ResponseEntity<String> result = productController.saveProduct(productRequestDto);
		verify(productService).saveProduct(productRequestDto);
		assertEquals("new product added succesfully", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("Deleting product :Positive Scenario")
	public void deleteByIdTest1() {
		when(productService.deleteProduct(1)).thenReturn("deleted");
		ResponseEntity<String> result = productController.deleteProduct(1);
		verify(productService).deleteProduct(1);
		assertEquals("deleted", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());

	}

	@Test
	@DisplayName("Deleting Product :negative Scenario")
	public void deleteByIdTest2() {
		when(productService.deleteProduct(1)).thenReturn("Not deleted");
		ResponseEntity<String> result = productController.deleteProduct(1);
		verify(productService).deleteProduct(1);
		assertEquals("Not deleted", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());

	}

	@Test
	@DisplayName("update Product :Positive Scenario")
	public void updateByIdTest1() {
		when(productService.updateProduct(1, productRequestDto)).thenReturn("updated");
		ResponseEntity<String> result = productController.updateProduct(1, productRequestDto);
		verify(productService).updateProduct(1, productRequestDto);
		assertEquals("updated", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	@DisplayName("update Product :Negative Scenario")
	public void updateByIdTest() {
		when(productService.updateProduct(1, productRequestDto)).thenReturn("Not updated");
		ResponseEntity<String> result = productController.updateProduct(1, productRequestDto);
		verify(productService).updateProduct(1, productRequestDto);
		assertEquals("Not updated", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}
