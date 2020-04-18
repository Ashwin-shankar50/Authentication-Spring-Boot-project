package com.cares.cervello.login.service;

import org.springframework.stereotype.Service;

import com.cares.cervello.login.DTO.LoginResponseDTO;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public LoginResponseDTO valiadateUserByEmailIdAndPassword(String emailId, String password) {
		LoginResponseDTO loginResponseDTO = null;
		///////
		if (emailId.equalsIgnoreCase(password)) {
			loginResponseDTO = new LoginResponseDTO();
			loginResponseDTO.setEmailId(emailId);
		}
		///////
		return loginResponseDTO;
	}

}
