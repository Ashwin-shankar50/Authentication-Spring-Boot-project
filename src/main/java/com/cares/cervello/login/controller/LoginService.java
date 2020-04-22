package com.cares.cervello.login.controller;

public interface LoginService {

	LoginResponseDTO valiadateUserByEmailIdAndPassword(String emailId, String password) throws Exception;

}
