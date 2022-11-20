package com.maersk.container.bookings.webclient;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.maersk.container.bookings.config.AppConfig;
import com.maersk.container.bookings.model.AvailabilityCheckRequest;

/**
 * @author Sreelekha Class to make a call to Third part API to get the available
 *         space.
 */
@Component
public class ApiCaller {
	Logger logger = LoggerFactory.getLogger(ApiCaller.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	AppConfig appConfig;

	/**
	 * Method to invoke third party api to get the available container space.
	 * 
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> getAvaialbleContainer(AvailabilityCheckRequest request) {
		System.out.println("Enter into API Caller");
		System.out.println("ip " + appConfig.getHostName());
		System.out.println("protocal " + appConfig.getProtocal());
		HttpEntity<AvailabilityCheckRequest> entity = new HttpEntity<AvailabilityCheckRequest>(request,
				getAPIHeaders());
		String url = buildURL(appConfig.getProtocal(), appConfig.getHostName(), appConfig.getContextPath());
		logger.info("Third Part API URL: {}", url);
		return restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
	}

	private String buildURL(String protocal, String ip, String contextPath) {
		return new StringBuilder(protocal).append("://").append(ip).append("/").append(contextPath).toString();
	}

	/**
	 * Method to prepare required HttpHeaders to make a call to API
	 * 
	 * @return HttpHeaders
	 */
	public HttpHeaders getAPIHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;

	}

}
