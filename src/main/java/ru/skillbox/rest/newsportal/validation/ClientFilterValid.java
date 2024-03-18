package ru.skillbox.rest.newsportal.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ClientFilterValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientFilterValid {

    String message() default "Поля пагинации должны быть указаны!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
