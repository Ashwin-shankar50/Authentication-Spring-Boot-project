package com.cares.cervello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "userDetails")
@Data
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int userId;

	private String userName;

	private String emailId;

	private String password;

	private String mobileNumber;

	private String userType;

	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", emailId=" + emailId + ", password="
				+ password + ", mobileNumber=" + mobileNumber + ", userType=" + userType + "]";
	}

}