package com.maersk.container.bookings.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.maersk.container.bookings.config.AppConfig;
import com.maersk.container.bookings.entity.Bookings;
import com.maersk.container.bookings.model.AvailabilityCheckRequest;
import com.maersk.container.bookings.model.AvailabilityCheckResponse;
import com.maersk.container.bookings.model.AvailabilityContainersResponse;
import com.maersk.container.bookings.model.BookingRequest;
import com.maersk.container.bookings.model.BookingResponse;
import com.maersk.container.bookings.repository.BookingRepository;
import com.maersk.container.bookings.webclient.ApiCaller;

import reactor.core.publisher.Mono;

/**
 * @author Sreelekha
 *
 */

@Service
public class BookingContainerService {

	Logger logger = LoggerFactory.getLogger(BookingContainerService.class);

	@Autowired
	ApiCaller apiCaller;

	@Autowired
	AppConfig config;

	@Autowired
	BookingRepository repo;

	/**
	 * Method to get the available space from third part API and prepare response
	 * 
	 * @param request payload of AvailabilityCheckRequest
	 * @return AvailabilityCheckResponse
	 */
	public AvailabilityCheckResponse checkAvailability(AvailabilityCheckRequest request) {
		boolean apiMock = false;
		logger.debug("Request reached to Service layer");
		ResponseEntity<?> apiResponse = null;
		/*
		 * Since checkAvaialble is Dummy API added boolean flag if it true it will make
		 * a call to API using RestTemplate else Dummy response will be returned
		 */
		if (apiMock) {
			apiResponse = apiCaller.getAvaialbleContainer(request);
			logger.info("Response of Third Party API {} ", apiResponse.getBody().toString());
		} else {
			apiResponse = ResponseEntity.status(HttpStatus.OK)
					.body(new AvailabilityContainersResponse(config.getCount()));
			logger.info("Response of Third Party API {} ", apiResponse.getBody().toString());
		}

		AvailabilityCheckResponse response = new AvailabilityCheckResponse();
		response.setAvailable(isContainerAvailable(apiResponse));
		return response;
	}

	/**
	 * Method to book a Container with the given input Parameters in Cassandra
	 * DataBase
	 * 
	 * @param bookingRequest
	 * @return
	 */
	public BookingResponse bookContainer(BookingRequest bookingRequest) {

		Long recordCount = repo.count().block();
		Mono<Bookings> booking = repo.save(dataConverter(bookingRequest, recordCount));
		BookingResponse response = new BookingResponse(booking.block().getId());
		return response;

	}

	private Bookings dataConverter(BookingRequest bookingRequest, Long recordCount) {
		Bookings bookings = new Bookings();
		bookings.setContainerSize(bookingRequest.getContainerSize());
		bookings.setContainerType(bookingRequest.getContainerType());
		bookings.setDestination(bookingRequest.getDestination());
		bookings.setOrigin(bookingRequest.getOrigin());
		bookings.setQuantity(bookingRequest.getQuantity());
		bookings.setTimestamp(convertStringToTimestamp(bookingRequest.getTimestamp()));
		bookings.setId(957000001L + recordCount);
		return bookings;
	}

	/**
	 * Method to convert String to DateFormat
	 * 
	 * @param timestamp
	 * @return Date in LocalDateTime format
	 */
	private LocalDateTime convertStringToTimestamp(String timestamp) {
		LocalDateTime dateTime = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_INSTANT);
		return dateTime;
	}

	/**
	 * Method to check the container available space returned from API and retun
	 * true or false
	 * 
	 * @param response
	 * @return boolean
	 */
	public boolean isContainerAvailable(ResponseEntity<?> response) {
		int count = 0;
		if (response.getStatusCode() == HttpStatus.OK) {
			AvailabilityContainersResponse resp = (AvailabilityContainersResponse) response.getBody();
			count = resp.getAvailableSpace();
			logger.info("Available Space {} ", count);
		}
		return (count > 0 ? true : false);
	}

}
