package com.maersk.container.bookings.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.container.bookings.model.*;
import com.maersk.container.bookings.model.AvailabilityCheckResponse;
import com.maersk.container.bookings.service.BookingContainerService;

@Validated
@RequestMapping(value = "/api/bookings")
@RestController
public class ContainerBookingsController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(ContainerBookingsController.class);

	@Autowired
	BookingContainerService service;

	@PostMapping(value = "/checkAvailable", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AvailabilityCheckResponse> availabilityCheck(
			@Valid @RequestBody AvailabilityCheckRequest availabilityRequest) {
		AvailabilityCheckResponse response = service.checkAvailability(availabilityRequest);
		this.logger.info("Third party API Response: {}", response.isAvailable());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
