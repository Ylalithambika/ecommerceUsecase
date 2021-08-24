package com.example.ecommerce.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.entity.Product;

public interface ProductService {

	String saveProduct(ProductRequestDto productRequestDto);

	List<Product> getProducts();

	String updateProduct(long productId, ProductRequestDto productRequestDto);

	String deleteProduct(long productId);

	List<Product> searchProduct(String productName, String category);

}
