package com.cares.cervello.registration.controller;

import com.cares.cervello.exception.DatabaseAccessException;
import com.cares.cervello.exception.EmailIdAlreadyExistException;

public interface RegistrationService {

	RegistrationResponseDTO updateNewUser(RegistrationRequestDTO request)
			throws DatabaseAccessException, EmailIdAlreadyExistException;
}
