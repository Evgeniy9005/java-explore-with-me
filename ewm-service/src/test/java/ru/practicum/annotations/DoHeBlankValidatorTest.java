package ru.practicum.annotations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoHeBlankValidatorTest extends DoDateArrivedValidatorTest {

    private DoHeBlankValidator doHeBlankValidator;

    @BeforeEach
    void setUp() {
        doHeBlankValidator = new DoHeBlankValidator();
        value = "   ";
    }

    @Test
    void isValid() {
        assertFalse(doHeBlankValidator.isValid(value,constraintValidatorContext));
    }
}