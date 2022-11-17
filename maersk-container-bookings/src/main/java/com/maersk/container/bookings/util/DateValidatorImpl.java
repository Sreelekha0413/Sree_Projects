package com.maersk.container.bookings.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.cfg.context.Constrainable;
import org.springframework.validation.annotation.Validated;

import com.maersk.container.bookings.annotations.DateValidator;

public class DateValidatorImpl implements ConstraintValidator<DateValidator, String>{

	private boolean isOptional;
	@Override
	public void initialize(DateValidator date) {
		this.isOptional=date.optional();
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
	        Instant.from(DateTimeFormatter.ISO_INSTANT.parse(value));
	        return true;
	    } catch (DateTimeParseException e) {
	       return false;
	    }
	}
	
	
}
