package com.cares.cervello.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginByUserNameAndPassword(@RequestBody LoginRequestDTO request)
			throws Exception {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		LoginResponseDTO loginResponseDTO = loginService
				.validateUserByEmailIdAndPassword(request.getEmailId().toLowerCase(), (request.getPassword()));
		if (loginResponseDTO != null) {
			status = HttpStatus.OK;
		}
		ResponseEntity<LoginResponseDTO> response = new ResponseEntity<LoginResponseDTO>(loginResponseDTO, status);
		return response;
	}

}
