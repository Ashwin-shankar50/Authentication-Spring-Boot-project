package com.cares.cervello.registration.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cares.cervello.entity.UserDetails;
import com.cares.cervello.utility.PasswordManager;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	RegistrationQueryRepository registrationRepository;

	@Override
	public RegistrationResponseDTO updateNewUser(RegistrationRequestDTO request) throws Exception {
		System.out.println("Updating the new user details to DB");
		RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO();
		try {
			/*
			 * String enc = PasswordManager.encryptString(request.getPassword());
			 * System.out.println("Encrypted : "+enc); String dec =
			 * PasswordManager.decryptString(enc); System.out.println("Decrypted : "+dec);
			 */
			List<UserDetails> userList = getUserDetails(request.getEmailId().toLowerCase());
			if (userList.size() > 0) {
				registrationResponseDTO.setRegistrationStatus("EMAIL_ID_ALREADY_USED"); // Checking if the user is
																						// already registered
			} else {
				registrationRepository.save(getRegistrationResponseDTOFromUserDetailsEntity(request)); // Inserting the
				// record
				registrationResponseDTO.setRegistrationStatus("SUCCESS"); // On successful insertion, assigning status
																			// flag as
				// true
			}

		} catch (Exception e) {
			e.printStackTrace();
			registrationResponseDTO.setRegistrationStatus("SERVER_ERROR"); // On failure insertion, assigning status
																			// flag as
			// false
		}

		return registrationResponseDTO;
	}

	public List<UserDetails> getUserDetails(String emailId) throws Exception {
		System.out.println("Verifying if this email ID is already registered ...");
		List<UserDetails> userList = null;
		try {
			userList = registrationRepository.findByEmailId(emailId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return userList;
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
