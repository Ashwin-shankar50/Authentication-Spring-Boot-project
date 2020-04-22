package com.cares.cervello.exception;

public class PasswordNotMatchedException extends Exception {

	private static final long serialVersionUID = 1L;

	public PasswordNotMatchedException(String message) {
		super(message);
	}
}
