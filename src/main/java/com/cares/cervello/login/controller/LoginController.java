package com.cares.cervello.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cares.cervello.exception.DatabaseAccessException;
import com.cares.cervello.exception.EmailIdNotFoundException;
import com.cares.cervello.exception.PasswordNotMatchedException;

@RestController
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginByUserNameAndPassword(@RequestBody LoginRequestDTO request)
			throws EmailIdNotFoundException, PasswordNotMatchedException, DatabaseAccessException {

		LOG.info("Entry : /login with {}", request);
		LoginResponseDTO loginResponseDTO;
		loginResponseDTO = loginService.valiadateUserByEmailIdAndPassword(request.getEmailId().toLowerCase(),
				request.getPassword());
		ResponseEntity<LoginResponseDTO> response = new ResponseEntity<LoginResponseDTO>(loginResponseDTO,
				HttpStatus.OK);
		LOG.info("Exit : /login with {}", response);

		return response;
	}

}