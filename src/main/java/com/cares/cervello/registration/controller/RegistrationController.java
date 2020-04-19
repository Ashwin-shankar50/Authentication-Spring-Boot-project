package com.cares.cervello.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

	private RegistrationService registrationService;

	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping("/register")
	public ResponseEntity<RegistrationResponseDTO> registerNewUser(@RequestBody RegistrationRequestDTO request)
			throws Exception {
		System.out.println("Request recieved");
		System.out.println(request.toString());
		HttpStatus status = HttpStatus.BAD_REQUEST;

		RegistrationResponseDTO registrationResponseDTO = registrationService.updateNewUser(request);

		if (registrationResponseDTO.getRegistrationStatus() == "SUCCESS") {
			status = HttpStatus.OK;
		}
		
		ResponseEntity<RegistrationResponseDTO> response = new ResponseEntity<RegistrationResponseDTO>(
				registrationResponseDTO, getResponseHeaders(), status);
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
