package com.maersk.container.bookings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.maersk.container.bookings.model.AvailabilityCheckRequest;
import com.maersk.container.bookings.model.AvailabilityCheckResponse;
import com.maersk.container.bookings.model.AvailabilityContainersResponse;
import com.maersk.container.bookings.model.BookingRequest;
import com.maersk.container.bookings.model.BookingResponse;
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
	@Spy
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
		Assertions.assertEquals(true, resp.isAvailable());

	}

	@Test
	public void testCheckAvailableApiFail_CheckAvailable() {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("DRY", 20, "Southampton", "Australia", 5);

		AvailabilityContainersResponse response = new AvailabilityContainersResponse(0);
		Mockito.when(api.getAvaialbleContainer(request))
				.thenReturn(new ResponseEntity<Object>(response, HttpStatus.OK));

		AvailabilityCheckResponse resp = service.checkAvailability(request);
		Assertions.assertEquals(false, resp.isAvailable());

	}

	@Test
	public void testBookingSuccess_ContainerBooking() {
		BookingRequest request = new BookingRequest("DRY", 25, "Southampton", "Australia", 100, "2022-11-12 13:53:09");
		BookingResponse resp = new BookingResponse(957000001L);
		Mockito.when(service.bookContainer(request)).thenReturn(resp);
		BookingResponse resp1 = service.bookContainer(request);
		Assertions.assertEquals(957000001L, resp1.getBookingRef());

	}

}
