package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.AddToCart;

public interface AddToCartRepository extends JpaRepository<AddToCart, Long> {

	AddToCart findByCartId(long cartId);

	

}
