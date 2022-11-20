package com.maersk.container.bookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.maersk.container.bookings","com.maersk.container.bookings.controller","com.maersk.container.bookings.config","com.maersk.container.bookings.webclient"}, exclude = {
		DataSourceAutoConfiguration.class })
public class MaerskContainerBookingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaerskContainerBookingsApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
