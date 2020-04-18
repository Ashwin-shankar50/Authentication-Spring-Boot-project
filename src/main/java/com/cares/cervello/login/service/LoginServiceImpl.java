package com.cares.cervello.login.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cares.cervello.login.DTO.LoginResponseDTO;
import com.cares.cervello.repository.MySqlConnector;

@Service
public class LoginServiceImpl implements LoginService {
	@Value("${app.dbHost}")
	private String dbHost;

	@Value("${app.dbUserName}")
	private String dbUserName;

	@Value("${app.dbPassword}")
	private String dbPassword;

	@Override
	public LoginResponseDTO valiadateUserByEmailIdAndPassword(String emailId, String password) {
		LoginResponseDTO loginResponseDTO = null;
		ResultSet rs = null;
		try {
			MySqlConnector con = new MySqlConnector(dbHost, dbUserName, dbPassword);
			Connection connection = con.establishConnection();
			Statement stmt = connection.createStatement();
			String query = "select userId, userName, emailId, mobileNumber, userType from CERVELLO_CARES.userDetails where emailId = '"
					+ emailId + "' and password = '" + password + "'";
			System.out.println(query);
			rs = stmt.executeQuery(query);
			rs.last();
			if (rs.getRow() == 1) {
				rs.first();
				loginResponseDTO = new LoginResponseDTO();
				loginResponseDTO.setEmailId(rs.getString("emailId"));
				loginResponseDTO.setUserName(rs.getString("userName"));
				loginResponseDTO.setMobileNumber(rs.getString("mobileNumber"));
				loginResponseDTO.setUserType(rs.getString("userType"));
				loginResponseDTO.setUserId(rs.getString("userId"));
			}
			/*
			 * System.out.println(); while (rs.next()) {
			 * System.out.println(rs.getString("userName")); }
			 */
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return loginResponseDTO;
	}

}
