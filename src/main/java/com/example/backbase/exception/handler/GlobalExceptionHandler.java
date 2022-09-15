package com.example.backbase.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.backbase.exception.MovieException;
import com.example.backbase.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private static final String SERVER_SIDE_ERROR = "server side error";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnknownSystemException(WebRequest request, Exception e) {
		String message = e.getMessage();
		logger.error(message, e);
		String errorCode = HttpStatus.INTERNAL_SERVER_ERROR.name();
		String errorMessage = SERVER_SIDE_ERROR + "- " + e.getMessage();
		return errorResponse(errorMessage, errorCode, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<ErrorResponse> handleMovieException(WebRequest request, MovieException e) {
		logger.error(e.getMessage(), e);
		String errorCode = SERVER_SIDE_ERROR;
		return errorResponse(e.getMessage(), errorCode, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ErrorResponse> errorResponse(String errorMessage, String errorCode, HttpStatus httpStatus) {
		ErrorResponse errorResponse = new ErrorResponse(errorCode, Integer.valueOf(1), errorMessage);
		return new ResponseEntity<>(errorResponse, httpStatus);
	}
}
