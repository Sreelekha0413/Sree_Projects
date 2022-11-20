package com.maersk.container.bookings.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.maersk.container.bookings.annotations.StringEnumValidator;

/**
 * @author Sreelekha Class to validate if the value is in Enum list of not
 *
 */
public class StringEnumValidatorImpl implements ConstraintValidator<StringEnumValidator, String> {
	private List<String> enumValueList;

	@Override
	public void initialize(StringEnumValidator constraint) {
		enumValueList = new ArrayList<String>();
		for (String value : constraint.acceptedValues()) {
			enumValueList.add(value.toUpperCase());
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return enumValueList.contains(value.toUpperCase());
	}

}
