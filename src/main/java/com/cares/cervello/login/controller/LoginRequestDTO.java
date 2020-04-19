package com.cares.cervello.login.controller;

public class LoginRequestDTO {

	private String emailId;
	
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequestDTO [emailId=" + emailId + ", password=" + password + "]";
	}
}
