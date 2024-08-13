package ru.practicum.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DoHeBlankValidator implements ConstraintValidator<DoHeBlank, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value != null) {
            return !value.isBlank();
        }
        return true;
    }
}
