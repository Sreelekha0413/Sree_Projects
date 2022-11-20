package com.maersk.container.bookings;

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
public class ApiCallerTest {
	@Mock
	private RestTemplate restTemplate;

	@Autowired
	@InjectMocks
	private ApiCaller api;

	@Test
	public void testCheckAvailableApiSuccess_RestTemplate() {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("DRY", 20, "Southampton", "Australia", 5);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<AvailabilityCheckRequest> entity = new HttpEntity<AvailabilityCheckRequest>(request, headers);
		AvailabilityContainersResponse response = new AvailabilityContainersResponse(6);
		Mockito.when(restTemplate.exchange("https://maersk.com/api/bookings/checkAvailable", HttpMethod.POST, entity,
				Object.class)).thenReturn(new ResponseEntity<Object>(response, HttpStatus.OK));
		AvailabilityContainersResponse count = new AvailabilityContainersResponse(6);
		ResponseEntity<Object> expResp1 = api.getAvaialbleContainer(request);
		AvailabilityCheckResponse expResp = new AvailabilityCheckResponse();
		expResp.setAvailable(true);

		// AvailabilityCheckResponse resp = service.checkAvailability(request);
		System.out.println("After Service call: ");
		System.out.println("resp: " + expResp1.getStatusCode());
		System.out.println("resp message: " + expResp1.getBody().toString());
		Assertions.assertEquals(true, expResp1.getBody().toString().contains("6"));

	}

}
