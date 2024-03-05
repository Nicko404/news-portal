package ru.skillbox.rest.newsportal.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NewsCategoryValidValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NewsCategoryValid {

    String message() default "Поля пагинации должны быть указаны!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
