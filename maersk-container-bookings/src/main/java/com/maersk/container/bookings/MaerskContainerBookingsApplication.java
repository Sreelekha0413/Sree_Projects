package com.maersk.container.bookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MaerskContainerBookingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaerskContainerBookingsApplication.class, args);
	}
	
	 @Bean
		public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

}
