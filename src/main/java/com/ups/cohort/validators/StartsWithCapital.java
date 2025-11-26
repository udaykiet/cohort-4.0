package com.ups.cohort.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartsWithCapitalValidator.class)
public @interface StartsWithCapital {
	String message() default "Value must start with a capital letter";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
