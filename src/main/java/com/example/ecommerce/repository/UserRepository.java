package com.example.ecommerce.repository;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNameAndPassword(String userName, String password);

	User findByUserId(long l);

	@Query("from OrderDetails,User u where u.userId =:userId and month(date)=:month and Year(date)=:year")
	List<OrderDetails> getOrders(long userId, @Valid @Min(1) @Max(12) int month, @Valid @Min(2021) @Max(3000) int year);

	User findByUserName(String userName);

}
