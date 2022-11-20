package com.maersk.container.bookings.webclient;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.maersk.container.bookings.model.AvailabilityCheckRequest;

@Component
public class ApiCaller {
	Logger logger = LoggerFactory.getLogger(ApiCaller.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${maersk.booking.api.hostname}")
	private String ip;

	@Value("${maersk.booking.api.protocal}")
	private String protocal;

	@Value("${maersk.booking.api.contextPath}")
	private String contextPath;

	public ResponseEntity<Object> getAvaialbleContainer(AvailabilityCheckRequest request) {
		System.out.println("Enter into API Caller");
		System.out.println("ip " + ip);
		System.out.println("protocal " + protocal);
		System.out.println("contextPath " + contextPath);
		HttpEntity<AvailabilityCheckRequest> entity = new HttpEntity<AvailabilityCheckRequest>(request,
				getAPIHeaders());
		String url = buildURL(protocal, ip, contextPath);
		logger.info("Third Part API URL: {}", url);
		return restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
	}

	private String buildURL(String protocal, String ip, String contextPath) {
		return new StringBuilder(protocal).append("://").append(ip).append("/").append(contextPath).toString();
	}

	public HttpHeaders getAPIHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;

	}

}
