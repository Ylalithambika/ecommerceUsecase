package com.example.ecommerce.dto;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.ecommerce.entity.Address;

public class UserRequestDto {
	@NotEmpty(message = "feilds should not be empty")
	private String userName;

	@Email(message = "emaild id should be in proper format")

	private String emailId;

	@NotNull(message = "Gender should not be null")
	private String gender;

	@NotEmpty(message = "password should not be empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
	private String password;

	@NotEmpty(message = "password should not be empty")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
	private String confrimPassword;

	@NotNull(message = "mobile number should be in proper format")
	@Pattern(regexp = "[0-9]{10}$")
	private String mobileNo;
	@NotNull(message = "Gender should not be null")

	@Embedded
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfrimPassword() {
		return confrimPassword;
	}

	public void setConfrimPassword(String confrimPassword) {
		this.confrimPassword = confrimPassword;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
