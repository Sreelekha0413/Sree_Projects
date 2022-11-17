package com.maersk.container.bookings.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import com.maersk.container.bookings.util.DateValidatorImpl;

@Documented
@Constraint(validatedBy = DateValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull(message = "Value cannot be null")
public @interface DateValidator {
	String message() default "Invalid Date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean optional() default false;

}
