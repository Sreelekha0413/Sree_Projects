package com.maersk.container.bookings.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.maersk.container.bookings.annotations.StringEnumValidator;

public class StringEnumValidatorImpl implements ConstraintValidator<StringEnumValidator, String>{
    private List<String> enumValueList;
    
    @Override
    public void initialize(StringEnumValidator constraint) {
    	enumValueList = new ArrayList<String>();
    	for(String value : constraint.validValues()) {
    		enumValueList.add(value.toUpperCase());
    	}
    }
    
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}
	

}
