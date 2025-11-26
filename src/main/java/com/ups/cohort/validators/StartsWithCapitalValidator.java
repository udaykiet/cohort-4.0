package com.ups.cohort.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartsWithCapitalValidator implements ConstraintValidator<StartsWithCapital,String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		return Character.isUpperCase(value.charAt(0));
	}
}
