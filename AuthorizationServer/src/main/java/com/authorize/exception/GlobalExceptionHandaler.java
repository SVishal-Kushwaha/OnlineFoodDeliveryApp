package com.authorize.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.authorize.dto.ErrorDetails;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandaler {

	@ExceptionHandler(UserNotFoundException.class)

	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		var error = ErrorDetails.builder().timestamp(new Date()).message(ex.getMessage())
				.details(request.getDescription(false)).build();
		log.error(ex.toString());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserPresentException.class)

	public ResponseEntity<?> handleUserPresentFoundException(UserPresentException ex, WebRequest request) {
		var error = ErrorDetails.builder().timestamp(new Date()).message(ex.getMessage())
				.details(request.getDescription(false)).build();
		log.error(ex.toString());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)

	public ResponseEntity<?> handleAllTypeException(Exception ex, WebRequest request) {
		var error = ErrorDetails.builder().timestamp(new Date()).message(ex.getMessage())
				.details(request.getDescription(false)).build();
		log.error(ex.toString());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
