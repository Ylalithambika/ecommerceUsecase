package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

	@Override
	public String saveProduct(ProductRequestDto productRequestDto) {
		Product product = new Product();

		BeanUtils.copyProperties(productRequestDto, product);
		List<Product> product1 = productRepository.findByProductNameAndCategory(productRequestDto.getProductName(),
				productRequestDto.getCategory());
		if (product1 != null) {
			Product product2 = productRepository.save(product);
			return "Product added";
		}
		return "product already exists";
	}

	@Override
	public List<Product> getProducts() {

		return productRepository.findAll();

	}

	@Override
	public String updateProduct(long productId, ProductRequestDto productRequestDto) {
		Product product = productRepository.findByProductId(productId);
		if (product != null) {
			BeanUtils.copyProperties(productRequestDto, product);

			productRepository.save(product);
			return "product updated";
		}

		else {
			return "Not Updated";
		}
	}

	@Override
	public String deleteProduct(long productId) {
		Product product = productRepository.findByProductId(productId);
		if (product != null) {
			productRepository.deleteById(productId);
			return "Product is deleted successfully";
		} else {
			return "Product doesnt Exists";
		}
	}

	@Override
	public List<Product> searchProduct(String productName, String category) {

		List<Product> products = productRepository.findByProductNameContainsAndCategory(productName, category);
		if (products != null)
			return products;
		else {
			return null;
		}
	}
}
