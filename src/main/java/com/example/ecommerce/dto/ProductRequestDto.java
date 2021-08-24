package com.example.ecommerce.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductRequestDto {
	@NotNull(message = "feild should not be empty")
	private String productName;

	@Min(100)
	private double Cost;
	@NotNull(message = "feild should not be empty")

	private String productDescription;
	@NotNull(message = "feild should not be empty")

	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getCost() {
		return Cost;
	}

	public void setCost(double cost) {
		Cost = cost;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
