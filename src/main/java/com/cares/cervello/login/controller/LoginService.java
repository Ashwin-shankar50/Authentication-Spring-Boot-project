package com.cares.cervello.login.controller;

import com.cares.cervello.exception.DatabaseAccessException;
import com.cares.cervello.exception.EmailIdNotFoundException;
import com.cares.cervello.exception.PasswordNotMatchedException;

public interface LoginService {

	LoginResponseDTO valiadateUserByEmailIdAndPassword(String emailId, String password)
			throws EmailIdNotFoundException, PasswordNotMatchedException, DatabaseAccessException;

}
