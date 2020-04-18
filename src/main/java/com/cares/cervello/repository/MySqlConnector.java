package com.cares.cervello.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {
	private String dbHost;
	private String dbUserName;
	private String dbPassword;

	public MySqlConnector(String dbHost, String dbUserName, String dbPassword) {
		this.dbHost = dbHost;
		this.dbUserName = dbUserName;
		this.dbPassword = dbPassword;
	}

	public Connection establishConnection() {
		try {
			// "jdbc:mysql://localhost:3306/CERVELLO_CARES"
			// root
			// root
			Class.forName("com.mysql.jdbc.Driver");
			Connection connector = DriverManager.getConnection(dbHost, dbUserName, dbPassword);
			return connector;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
