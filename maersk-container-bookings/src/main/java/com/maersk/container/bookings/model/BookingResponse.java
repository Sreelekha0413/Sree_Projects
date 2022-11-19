package com.maersk.container.bookings.model;

public class BookingResponse {
	private Long bookingRef;

	public Long getBookingRef() {
		return bookingRef;
	}

	public void setBookingRef(Long bookingRef) {
		this.bookingRef = bookingRef;
	}

	public BookingResponse(Long bookingRef) {
		super();
		this.bookingRef = bookingRef;
	}

}
