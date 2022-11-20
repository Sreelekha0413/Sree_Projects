package com.maersk.container.bookings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.container.bookings.controller.ContainerBookingsController;
import com.maersk.container.bookings.model.AvailabilityCheckRequest;
import com.maersk.container.bookings.model.BookingRequest;
import com.maersk.container.bookings.service.BookingContainerService;

@RunWith(SpringRunner.class)
@WebMvcTest(ContainerBookingsController.class)
class ContainerBookingsControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BookingContainerService service;

	protected String mapToJson(AvailabilityCheckRequest obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void testInvalidContainerTypeOfCheckAvailability() throws Exception {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("Wet", 20, "Southampton", "Australia", 5);

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailable")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(true, content.contains("Invalid Container Type. It must be either DRY or REFER"));

	}

	@Test
	public void testInvalidContainerSizeOfCheckAvailability() throws Exception {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("DRY", 25, "Southampton", "Australia", 5);

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailable")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(true, content.contains("Invalid Container Size. It must be either 20 or 40"));

	}

	@Test
	public void testInvalidOriginLengthOfCheckAvailability() throws Exception {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("DRY", 20, "Sout", "Australia", 5);

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailable")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(true, content.contains("length of origin value must be between 5 and 20"));

	}

	@Test
	public void testInvalidDestinationLengthOfCheckAvailability() throws Exception {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("DRY", 20, "Southampton", "Aust", 5);

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailable")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(true, content.contains("length of destination value must be between 5 and 20"));

	}

	@Test
	public void testInvalidQuantityOfCheckAvailability() throws Exception {
		AvailabilityCheckRequest request = new AvailabilityCheckRequest("DRY", 20, "Southampton", "Australia", 120);

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings/checkAvailable")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(true, content.contains("quentity must be less than 100"));

	}

	@Test
	public void testInvalidTimestampContainerBooking() throws Exception {
		BookingRequest request = new BookingRequest("DRY", 25, "Southampton", "Australia", 100, "2022-11-12 13:53:09");

		String inputJson = mapToJson(request);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/bookings")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(true, content.contains("Invalid Date. Date must be in ISO-8601 Format"));

	}

}
