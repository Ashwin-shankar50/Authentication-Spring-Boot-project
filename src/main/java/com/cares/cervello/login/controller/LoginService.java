package com.cares.cervello.login.controller;

public interface LoginService {

	LoginResponseDTO validateUserByEmailIdAndPassword(String emailId, String password) throws Exception;

}
