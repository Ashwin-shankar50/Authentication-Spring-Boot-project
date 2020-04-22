package com.cares.cervello.registration.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cares.cervello.entity.UserDetails;
import com.cares.cervello.exception.DatabaseAccessException;
import com.cares.cervello.exception.EmailIdAlreadyExistException;
import com.cares.cervello.utility.PasswordManager;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationQueryRepository registrationRepository;

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Override
	public RegistrationResponseDTO updateNewUser(RegistrationRequestDTO request)
			throws DatabaseAccessException, EmailIdAlreadyExistException {
		LOG.info("updateNewUser with {}", request);
		RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO();
		if (!isEmailIdAlreadyRegistered(request.getEmailId().toLowerCase())) {
			registrationRepository.save(getRegistrationResponseDTOFromUserDetailsEntity(request));
			registrationResponseDTO.setRegistrationStatus("SUCCESS");
			return registrationResponseDTO;
		}
		LOG.error("EmailIdAlreadyExistException : Given email id is alredy registered");
		throw new EmailIdAlreadyExistException("Given email id is alredy registered");
	}

	public Boolean isEmailIdAlreadyRegistered(String emailId) throws DatabaseAccessException {
		LOG.info("isEmailIdAlreadyRegistered with {}", emailId);
		List<UserDetails> userList = null;
		try {
			userList = registrationRepository.findByEmailId(emailId);
		} catch (Exception e) {
			LOG.error("Exception : {}", e);
			throw new DatabaseAccessException("Database connection failed");
		}
		return userList.size() > 0 ? true : false;
	}

	public UserDetails getRegistrationResponseDTOFromUserDetailsEntity(RegistrationRequestDTO request) {
		UserDetails newUserDetails = new UserDetails();
		newUserDetails.setEmailId(Optional.ofNullable(request.getEmailId().toLowerCase()).orElse(null));
		newUserDetails.setMobileNumber(Optional.ofNullable(request.getMobileNumber()).orElse(null));
		newUserDetails.setUserName(Optional.ofNullable(request.getUserName()).orElse(null));
		newUserDetails.setUserType(Optional.ofNullable(request.getUserType()).orElse(null));
		newUserDetails
				.setPassword(Optional.ofNullable(PasswordManager.encryptString(request.getPassword())).orElse(null));
		return newUserDetails;
	}
}
