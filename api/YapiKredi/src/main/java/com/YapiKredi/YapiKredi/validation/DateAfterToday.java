package com.YapiKredi.YapiKredi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class DateAfterToday implements ConstraintValidator<DateValidation, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return new Date().before(date);
    }
}
