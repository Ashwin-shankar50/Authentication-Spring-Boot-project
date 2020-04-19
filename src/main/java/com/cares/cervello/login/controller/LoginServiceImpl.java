package com.cares.cervello.login.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cares.cervello.entity.UserDetails;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginQueryRepository loginRepository;

	@Override
	public LoginResponseDTO valiadateUserByEmailIdAndPassword(String emailId, String password) throws Exception {
		System.out.println("Validation request recieved");
		LoginResponseDTO loginResponseDTO = null;
		try {
			List<UserDetails> userList = getUserDetails(emailId, password);
			// userList.stream().forEach((user) -> System.out.println(user));
			if (userList.size() == 1) {
				loginResponseDTO = getLoginResponseDTOFromUserDetailsEntity(userList.get(0));
			} else {
				throw new Exception("DB Handle issue : More than 1 User found for the provided credentials");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return loginResponseDTO;
	}

	public List<UserDetails> getUserDetails(String emailId, String password) throws Exception {
		System.out.println("Authentication verification against DB. Running...");
		List<UserDetails> userList = null;
		try {
			userList = loginRepository.findByEmailIdAndPassword(emailId, password);
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
