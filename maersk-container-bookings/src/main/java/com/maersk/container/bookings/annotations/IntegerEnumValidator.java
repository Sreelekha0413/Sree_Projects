package com.maersk.container.bookings.annotations;

import java.lang.annotation.Documented;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import com.maersk.container.bookings.util.IntegerEnumValidatorImpl;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IntegerEnumValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull(message = "Value cannot be null")
public @interface IntegerEnumValidator {
	public int[] validValues();

	  String message() default "Value is not valid";

	  Class<?>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};
}
