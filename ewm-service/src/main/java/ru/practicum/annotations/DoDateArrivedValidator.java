package ru.practicum.annotations;

import ru.practicum.util.Util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DoDateArrivedValidator implements ConstraintValidator<DoDateArrived, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
                //Изменение даты события на уже наступившую
            return LocalDateTime.now().isBefore(Util.getDate(value));
        }
        return true;
    }
}
