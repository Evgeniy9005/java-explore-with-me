package ru.practicum.annotations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IdsValidateValidatorTest extends DoDateArrivedValidatorTest {

    private IdsValidateValidator idsValidateValidator;

    private List<Integer> integerList = List.of(1,2,0,5);

    @BeforeEach
    void setUp() {
        idsValidateValidator = new IdsValidateValidator();
    }

    @Test
    void isValid() {
        assertFalse(idsValidateValidator.isValid(integerList,constraintValidatorContext));
    }
}