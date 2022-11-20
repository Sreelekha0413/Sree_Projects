package com.maersk.container.bookings;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.maersk.container.bookings.model.AvailabilityCheckRequest;
import com.maersk.container.bookings.model.AvailabilityCheckResponse;
import com.maersk.container.bookings.model.AvailabilityContainersResponse;
import com.maersk.container.bookings.service.BookingContainerService;
import com.maersk.container.bookings.webclient.ApiCaller;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class BookingContainerServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@Autowired
	@InjectMocks
	private BookingContainerService service;

	@Mock
	ApiCaller api;

	@Test
	public void testCheckAvailableApiSuccess_CheckAvailable() {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("DRY", 20, "Southampton", "Australia", 5);

		AvailabilityContainersResponse response = new AvailabilityContainersResponse(6);
		Mockito.when(api.getAvaialbleContainer(request))
				.thenReturn(new ResponseEntity<Object>(response, HttpStatus.OK));

		AvailabilityCheckResponse resp = service.checkAvailability(request);
		System.out.println("After Service call: ");
		System.out.println("resp message: " + resp.isAvailable());
		Assertions.assertEquals(true, resp.isAvailable());

	}

	@Test
	public void testCheckAvailableApiFail_CheckAvailable() {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("DRY", 20, "Southampton", "Australia", 5);

		AvailabilityContainersResponse response = new AvailabilityContainersResponse(0);
		Mockito.when(api.getAvaialbleContainer(request))
				.thenReturn(new ResponseEntity<Object>(response, HttpStatus.OK));

		AvailabilityCheckResponse resp = service.checkAvailability(request);
		System.out.println("After Service call: ");
		System.out.println("resp message: " + resp.isAvailable());
		Assertions.assertEquals(false, resp.isAvailable());

	}

	
}
