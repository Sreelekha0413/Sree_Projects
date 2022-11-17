package com.maersk.container.bookings.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.maersk.container.bookings.annotations.DateValidator;

public class BookingRequest extends AvailabilityCheckRequest {

	@DateValidator(message = "Invalid Date. Date must be in ISO-8601 Format")
	private String timestamp;

	public BookingRequest(@NotEmpty(message = "containerType is required") String containerType,
			@NotNull(message = "containerSize is required") int containerSize,
			@NotEmpty(message = "orgin is required") @Size(min = 5, max = 20, message = "length of origin value must be between 5 and 20") String origin,
			@NotEmpty(message = "orgin is required") @Size(min = 5, max = 20, message = "length of destination value must be between 5 and 20") String destination,
			@NotNull(message = "orgin is required") @Min(value = 1, message = "quentity must be greater than 0.") @Max(value = 1, message = "quentity must be less than 100.") int quantity,
			String timestamp) {
		super(containerType, containerSize, origin, destination, quantity);
		this.timestamp = timestamp;
	}

	

}
