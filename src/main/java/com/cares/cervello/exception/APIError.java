package com.cares.cervello.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class APIError {

	private Integer status;

	private HttpStatus httpStatus;

	private String message;

	private String debugMessage;

	private String errorCode;

}
