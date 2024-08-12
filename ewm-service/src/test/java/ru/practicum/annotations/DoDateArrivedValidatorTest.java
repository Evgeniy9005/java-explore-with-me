package ru.practicum.annotations;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.practicum.util.Util;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DoDateArrivedValidatorTest {

    @Mock
    protected ConstraintValidatorContext constraintValidatorContext;

    private DoDateArrivedValidator doDateArrivedValidator;

    protected LocalDateTime localDateTime;

    protected String value;

    @BeforeEach
    void setUp() {
        doDateArrivedValidator = new DoDateArrivedValidator();
        localDateTime = LocalDateTime.now();
        value = localDateTime.format(Util.getFormatter());
    }

    @Test
    void isValid() {
        assertFalse(doDateArrivedValidator.isValid(value,constraintValidatorContext));
    }
}