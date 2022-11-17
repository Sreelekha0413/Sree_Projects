package com.maersk.container.bookings.model;

public class AvailabilityContainersResponse {
	private int availableSpace;

	public AvailabilityContainersResponse(int availableSpace) {
		super();
		this.availableSpace = availableSpace;
	}

	@Override
	public String toString() {
		return "AvailabilityContainersResponse [availableSpace=" + availableSpace + "]";
	}

	public int getAvailableSpace() {
		return availableSpace;
	}

	public void setAvailableSpace(int availableSpace) {
		this.availableSpace = availableSpace;
	}
}
