/** 
 * Copyright 2020 by Tagit Ltd. (http://www.tagitmobile.com)
 *
 * This source code is the exclusive property of Tagit Pte and is protected 
 * by Singapore. and international copyright laws. Any other use, including the 
 * reproduction, modification, distribution, transmission, republication, 
 * display, or performance, of the source code is strictly prohibited.
 *
 * Developed by Tagit Pte (http://www.tagitmobile.com)
 */
package com.cares.cervello.login.service;

import com.cares.cervello.login.DTO.LoginResponseDTO;

public interface LoginService {

	LoginResponseDTO valiadateUserByEmailIdAndPassword(String emailId, String password);

}
