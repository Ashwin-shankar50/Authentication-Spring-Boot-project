package com.cares.cervello.registration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

	@PostMapping("/register")
	public ResponseEntity<RegistrationResponseDTO> registerNewUser(@RequestBody RegistrationRequestDTO request)
			throws Exception {
		LOG.info("Entry : /register with {}", request);
		RegistrationResponseDTO registrationResponseDTO = registrationService.updateNewUser(request);
		ResponseEntity<RegistrationResponseDTO> response = new ResponseEntity<RegistrationResponseDTO>(
				registrationResponseDTO, HttpStatus.OK);
		LOG.info("Exit : /login with {}", response);

		return response;
	}

}
