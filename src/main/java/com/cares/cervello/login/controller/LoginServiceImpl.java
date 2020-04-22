package com.cares.cervello.login.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cares.cervello.entity.UserDetails;
import com.cares.cervello.exception.DatabaseAccessException;
import com.cares.cervello.exception.EmailIdNotFoundException;
import com.cares.cervello.exception.PasswordNotMatchedException;
import com.cares.cervello.utility.PasswordManager;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginQueryRepository loginRepository;

	private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Override
	public LoginResponseDTO valiadateUserByEmailIdAndPassword(String emailId, String password)
			throws EmailIdNotFoundException, PasswordNotMatchedException, DatabaseAccessException {

		LOG.info("valiadateUserByEmailIdAndPassword with {},{}", emailId, password);
		List<UserDetails> userList = new ArrayList<UserDetails>();
		userList = getUserDetails(emailId);
		if (userList != null && userList.size() == 1) {
			if (validatePassword(userList.get(0).getPassword(), password)) {
				LoginResponseDTO loginResponseDTO = getLoginResponseDTOFromUserDetailsEntity(userList.get(0));
				return loginResponseDTO;
			} else {
				LOG.error("PasswordNotMatchedException : Given password is invalid");
				throw new PasswordNotMatchedException("Given password is invalid");
			}
		} else {
			LOG.error("EmailIdNotFoundException : There are no user registered with giving mail id");
			throw new EmailIdNotFoundException("There are no user registered with giving mail id");
		}
	}

	public Boolean validatePassword(String dbPass, String thisPass) {

		return PasswordManager.decryptString(dbPass).equals(thisPass) ? true : false;
	}

	public List<UserDetails> getUserDetails(String emailId) throws DatabaseAccessException {

		LOG.info("getUserDetails with {}", emailId);
		List<UserDetails> userList = null;
		try {
			userList = loginRepository.findByEmailId(emailId);
			return userList;
		} catch (Exception e) {
			LOG.error("Exception : {}", e);
			throw new DatabaseAccessException("Database connection failed");
		}

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
