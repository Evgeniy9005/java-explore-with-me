package ru.practicum.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = DoHeBlankValidator.class)
@Documented
public @interface DoHeBlank {
    String message() default "{Значение не должно содержать только пробелы!}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
