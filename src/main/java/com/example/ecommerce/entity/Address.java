package com.example.ecommerce.entity;

import javax.persistence.Embeddable;

@Embeddable // used to specify the address class will be used as a component 
public class Address {
	private String doorNo;
	private String city;
	private String state;
	private long pincode;

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
}
