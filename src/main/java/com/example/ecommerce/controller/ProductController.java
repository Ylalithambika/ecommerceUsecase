package com.example.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping
	public ResponseEntity<String> saveProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
		String product1 = productService.saveProduct(productRequestDto);
		return new ResponseEntity<String>("new product added succesfully", HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> updateProduct(@RequestParam long ProductId,
			@RequestBody ProductRequestDto productRequestDto) {
		return new ResponseEntity<String>(productService.updateProduct(ProductId, productRequestDto), HttpStatus.OK);

	}

	@DeleteMapping

	public ResponseEntity<String> deleteProduct(@RequestParam long productId) {
		return new ResponseEntity<String>(productService.deleteProduct(productId), HttpStatus.OK);

	}

	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam String productName,
			@RequestParam String category) {
		List<Product> products = productService.searchProduct(productName, category);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
