package com.maersk.container.bookings.service;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.maersk.container.bookings.model.AvailabilityCheckRequest;
import com.maersk.container.bookings.model.AvailabilityCheckResponse;
import com.maersk.container.bookings.model.AvailabilityContainersResponse;
import com.maersk.container.bookings.webclient.ApiCaller;

@Service
@Component
public class BookingContainerService {
	
	Logger logger = LoggerFactory.getLogger(BookingContainerService.class);
	
	@Autowired
	ApiCaller apiCaller;
	
	@Value("${maersk.booking.container.count}")
	private int containerCount;
	
	public AvailabilityCheckResponse checkAvailability(AvailabilityCheckRequest request) {
		boolean apiMock = false;
		logger.debug("Request reached to Service layer");
		ResponseEntity<?> apiResponse = null;
		if(apiMock) {
			apiResponse = apiCaller.getAvaialbleContainer(request);
		} else {
			System.out.println("Enter into service else");
			apiResponse = ResponseEntity.status(HttpStatus.OK).body(new AvailabilityContainersResponse(containerCount));
			logger.info("Response of Third Party API {} ",apiResponse.getBody().toString());
		}
		
		AvailabilityCheckResponse response = new AvailabilityCheckResponse();
		response.setAvailable(isContainerAvailable(apiResponse));
		return response;
	}
	
	public boolean isContainerAvailable(ResponseEntity<?> response) {
		int count = 0;
		System.out.println("Enter into isContainerAvailable");
		if(response.getStatusCode() == HttpStatus.OK) {
			AvailabilityContainersResponse resp = (AvailabilityContainersResponse) response.getBody();
			count = resp.getAvailableSpace();
			logger.info("Container Size {} ",count);
		}
		return (count>0?true:false);
	}

}
