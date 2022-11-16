package com.maersk.container.bookings.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.maersk.container.bookings.annotations.IntegerEnumValidator;
import com.maersk.container.bookings.annotations.StringEnumValidator;

import lombok.Data;

@Data
public class AvailabilityCheckRequest {
	@NotEmpty(message = "containerType is required")
	@StringEnumValidator(validValues= {"DRY","REFER"}, message = "Invalid Container Type. It must be either DRY or REFER")
	private String containerType;
	
	@NotNull(message = "containerSize is required")
	@IntegerEnumValidator(validValues = {20,40}, message = "Invalid Container Size. It must be either 20 or 40")
	private int containerSize;
	
	@NotEmpty(message = "orgin is required")
	@Size(min = 5, max = 20, message = "length of origin value must be between 5 and 20")
	private String origin;
	
	@NotEmpty(message = "orgin is required")
	@Size(min = 5, max = 20, message = "length of destination value must be between 5 and 20")
	private String destination;
	
	@NotNull(message = "orgin is required")
	@Min(value = 1, message = "quentity must be greater than 0.")
	@Max(value = 1, message = "quentity must be less than 100.")
	private int quantity;

}
