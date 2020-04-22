package com.cares.cervello.exception;

public class EmailIdAlreadyExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailIdAlreadyExistException(String message) {
		super(message);
	}
}
