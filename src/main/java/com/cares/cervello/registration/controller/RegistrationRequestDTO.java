package com.cares.cervello.registration.controller;

import lombok.Data;

@Data
public class RegistrationRequestDTO {

	private int userId;

	private String userName;

	private String emailId;

	private String password;

	private String mobileNumber;

	private String userType;

}
