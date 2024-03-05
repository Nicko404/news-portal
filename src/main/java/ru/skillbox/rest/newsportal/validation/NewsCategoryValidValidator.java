package ru.skillbox.rest.newsportal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;
import ru.skillbox.rest.newsportal.web.model.request.NewsCategoryFilter;

public class NewsCategoryValidValidator implements ConstraintValidator<NewsCategoryValid, NewsCategoryFilter> {

    @Override
    public boolean isValid(NewsCategoryFilter newsCategoryFilter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(newsCategoryFilter.getPageNumber(), newsCategoryFilter.getPageSize());
    }
}
