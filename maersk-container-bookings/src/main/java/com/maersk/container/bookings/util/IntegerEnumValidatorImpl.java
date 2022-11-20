package com.maersk.container.bookings.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.maersk.container.bookings.annotations.IntegerEnumValidator;


/**
 * @author Sreelekha
 * Class to validate if the input value is in Enum list or not
 *
 */
public class IntegerEnumValidatorImpl implements ConstraintValidator<IntegerEnumValidator, Integer>{

	private List<Integer> enumValueList;
	@Override
	public void initialize(IntegerEnumValidator constraint) {
		enumValueList = new ArrayList<Integer>();
		for(int value : constraint.acceptedValues()) {
			enumValueList.add(value);
		}
		System.out.println(enumValueList);
		
	}
	
	/**
	 * validate if the value is in Enum list or not
	 */
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return enumValueList.contains(value);
	}

}
