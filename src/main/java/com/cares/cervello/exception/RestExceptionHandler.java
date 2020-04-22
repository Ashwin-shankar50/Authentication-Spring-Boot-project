package com.cares.cervello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmailIdNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(EmailIdNotFoundException ex) {
		APIError apiError = new APIError();
		apiError.setStatus(403);
		apiError.setHttpStatus(HttpStatus.FORBIDDEN);
		apiError.setMessage(ex.getMessage());
		apiError.setErrorCode("NF_EMAIL_ID");
		apiError.setDebugMessage(ex.toString());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(PasswordNotMatchedException.class)
	public ResponseEntity<Object> handlePasswordNotMatchingException(PasswordNotMatchedException ex) {
		APIError apiError = new APIError();
		apiError.setStatus(403);
		apiError.setHttpStatus(HttpStatus.FORBIDDEN);
		apiError.setMessage(ex.getMessage());
		apiError.setErrorCode("INV_PASS");
		apiError.setDebugMessage(ex.toString());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(DatabaseAccessException.class)
	public ResponseEntity<Object> handleDatabaseAccessException(DatabaseAccessException ex) {
		APIError apiError = new APIError();
		apiError.setStatus(500);
		apiError.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(ex.getMessage());
		apiError.setErrorCode("ERR_DB");
		apiError.setDebugMessage(ex.toString());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(EmailIdAlreadyExistException.class)
	public ResponseEntity<Object> handleEmailAlredyExistException(EmailIdAlreadyExistException ex) {
		APIError apiError = new APIError();
		apiError.setStatus(400);
		apiError.setHttpStatus(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		apiError.setErrorCode("ALRD_EXT_EMAIL_ID");
		apiError.setDebugMessage(ex.toString());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
		return new ResponseEntity<>(apiError, apiError.getHttpStatus());
	}
}
