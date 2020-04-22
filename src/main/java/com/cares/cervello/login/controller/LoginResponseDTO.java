package com.cares.cervello.login.controller;

import lombok.Data;

@Data
public class LoginResponseDTO {
	
	private int userId;

	private String userName;

	private String emailId;

	private String mobileNumber;

	private String userType;

}
