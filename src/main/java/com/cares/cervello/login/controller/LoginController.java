package com.cares.cervello.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cares.cervello.login.DTO.LoginRequestDTO;
import com.cares.cervello.login.DTO.LoginResponseDTO;
import com.cares.cervello.login.service.LoginService;
import com.cares.cervello.login.service.LoginServiceImpl;

@RestController
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginServiceImpl loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginByUserNameAndPassword(@RequestBody LoginRequestDTO request) {
		HttpStatus status = HttpStatus.OK;
		LoginResponseDTO loginResponseDTO = loginService.valiadateUserByEmailIdAndPassword(request.getEmailId(),
				request.getPassword());
		if (loginResponseDTO == null) {
			loginResponseDTO = null;
			status = HttpStatus.BAD_REQUEST;
		}
		ResponseEntity<LoginResponseDTO> response = new ResponseEntity<LoginResponseDTO>(loginResponseDTO, status);
		return response;
	}
}
