package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.AddToCart;
import com.example.ecommerce.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

	

}
