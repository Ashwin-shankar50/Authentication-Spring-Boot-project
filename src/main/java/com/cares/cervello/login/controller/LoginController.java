package com.cares.cervello.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*, *")
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginByUserNameAndPassword(@RequestBody LoginRequestDTO request)
			throws Exception {
		System.out.println("Request recieved");
		System.out.println(request.toString());
		System.out.println(request.getEmailId() + " : " + request.getPassword());
		HttpStatus status = HttpStatus.OK;
		LoginResponseDTO loginResponseDTO = loginService
				.valiadateUserByEmailIdAndPassword(request.getEmailId().toLowerCase(), (request.getPassword()));
		if (loginResponseDTO == null) {
			loginResponseDTO = null;
			status = HttpStatus.BAD_REQUEST;
		}
		ResponseEntity<LoginResponseDTO> response = new ResponseEntity<LoginResponseDTO>(loginResponseDTO,
				getResponseHeaders(), status);
		System.out.println("Response sent");
		return response;
	}

	public HttpHeaders getResponseHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		// responseHeaders.set("Access-Control-Allow-Origin", "*");
		// responseHeaders.set("Access-Control-Allow-Headers", "*, *");
		responseHeaders.set("Access-Control-Allow-Origin", null);
		// responseHeaders.set("Access-Control-Allow-Credentials", "true");
		responseHeaders.set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		// responseHeaders.set("Access-Control-Max-Age", "3600");
		responseHeaders.set("Access-Control-Allow-Headers", "Content-Type, Accept");
		return responseHeaders;
	}
}
