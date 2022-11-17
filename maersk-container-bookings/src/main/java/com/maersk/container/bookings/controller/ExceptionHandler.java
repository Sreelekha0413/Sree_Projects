package com.maersk.container.bookings.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.maersk.container.bookings.model.ErrorResponse;
import com.maersk.container.bookings.webclient.ApiCaller;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, List<String>> errorResponse = new HashMap<>();
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

		errorResponse.put("errors", errors);
		logger.error("Invalid Request Body Parameters: {} ", errorResponse.get("errors"));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<?> handleApiClientException(HttpClientErrorException ex) {
		logger.error("Error from Third Party API : errorCode: {} and errorResponse {}", ex.getStatusCode(),
				ex.getResponseBodyAsString());

		return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<?> handleApiServerException(HttpServerErrorException ex) {
		logger.error("Error from Third Party API : errorCode: {} and errorResponse {}", ex.getStatusCode(),
				ex.getResponseBodyAsString());

		return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		ErrorResponse response = new ErrorResponse(500, "Sorry there was a problem processing your request");
		logger.error("Internal Server Error: {} ", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
