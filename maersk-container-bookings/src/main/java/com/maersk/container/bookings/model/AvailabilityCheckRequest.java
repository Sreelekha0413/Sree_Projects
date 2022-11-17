package com.maersk.container.bookings.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.maersk.container.bookings.annotations.IntegerEnumValidator;
import com.maersk.container.bookings.annotations.StringEnumValidator;

public class AvailabilityCheckRequest {

	@NotEmpty(message = "containerType is required")
	@StringEnumValidator(acceptedValues = { "DRY",
			"REFER" }, message = "Invalid Container Type. It must be either DRY or REFER")
	private String containerType;

	@NotNull(message = "containerSize is required")
	@IntegerEnumValidator(acceptedValues = { 20, 40 }, message = "Invalid Container Size. It must be either 20 or 40")
	private int containerSize;

	@NotEmpty(message = "orgin is required")
	@Size(min = 5, max = 20, message = "length of origin value must be between 5 and 20")
	private String origin;

	@NotEmpty(message = "orgin is required")
	@Size(min = 5, max = 20, message = "length of destination value must be between 5 and 20")
	private String destination;

	@NotNull(message = "quantity is required")
	@Min(value = 1, message = "quentity must be greater than 0.")
	@Max(value = 100, message = "quentity must be less than 100.")
	private int quantity;

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public int getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(int containerSize) {
		this.containerSize = containerSize;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public AvailabilityCheckRequest(@NotEmpty(message = "containerType is required") String containerType,
			@NotNull(message = "containerSize is required") int containerSize,
			@NotEmpty(message = "orgin is required") @Size(min = 5, max = 20, message = "length of origin value must be between 5 and 20") String origin,
			@NotEmpty(message = "orgin is required") @Size(min = 5, max = 20, message = "length of destination value must be between 5 and 20") String destination,
			@NotNull(message = "orgin is required") @Min(value = 1, message = "quentity must be greater than 0.") @Max(value = 1, message = "quentity must be less than 100.") int quantity) {
		super();
		this.containerType = containerType;
		this.containerSize = containerSize;
		this.origin = origin;
		this.destination = destination;
		this.quantity = quantity;
	}

}
