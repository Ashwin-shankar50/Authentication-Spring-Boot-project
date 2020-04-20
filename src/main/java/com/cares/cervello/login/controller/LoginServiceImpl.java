package com.cares.cervello.login.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cares.cervello.entity.UserDetails;
import com.cares.cervello.utility.PasswordManager;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginQueryRepository loginRepository;

	@Override
	public LoginResponseDTO validateUserByEmailIdAndPassword(String emailId, String password) throws Exception {
		System.out.println("Validation request recieved");
		LoginResponseDTO loginResponseDTO = null;
		try {
			List<UserDetails> userList = getUserDetails(emailId);
			if (userList.size() == 1) {
				if (validatePassword(userList.get(0).getPassword(), password)) {
					loginResponseDTO = getLoginResponseDTOFromUserDetailsEntity(userList.get(0));
				} else {
					throw new Exception("Wrong credentials. Password doesn't match");
				}
			} else if (userList.size() < 1) {
				throw new Exception("Email ID doesn't exist");
			} else {
				throw new Exception("DB Handle issue : More than 1 User found for the provided credentials");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return loginResponseDTO;
	}

	public Boolean validatePassword(String dbPass, String thisPass) {
		return PasswordManager.decryptString(dbPass).equals(thisPass) ? true : false;
	}

	public List<UserDetails> getUserDetails(String emailId) throws Exception {
		System.out.println("Authentication verification against DB. Running...");
		List<UserDetails> userList = null;
		try {
			userList = loginRepository.findByEmailId(emailId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return userList;
	}

	public LoginResponseDTO getLoginResponseDTOFromUserDetailsEntity(UserDetails userDetails) {
		LoginResponseDTO userDetailsDTO = new LoginResponseDTO();
		userDetailsDTO.setEmailId(Optional.ofNullable(userDetails.getEmailId()).orElse(null));
		userDetailsDTO.setMobileNumber(Optional.ofNullable(userDetails.getMobileNumber()).orElse(null));
		userDetailsDTO.setUserId(Optional.ofNullable(userDetails.getUserId()).orElse(null));
		userDetailsDTO.setUserName(Optional.ofNullable(userDetails.getUserName()).orElse(null));
		userDetailsDTO.setUserType(Optional.ofNullable(userDetails.getUserType()).orElse(null));
		return userDetailsDTO;
	}

}
