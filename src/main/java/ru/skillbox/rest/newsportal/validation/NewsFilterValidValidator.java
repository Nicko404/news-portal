package ru.skillbox.rest.newsportal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;
import ru.skillbox.rest.newsportal.web.model.request.NewsFilter;

public class NewsFilterValidValidator implements ConstraintValidator<NewsFilterValid, NewsFilter> {

    @Override
    public boolean isValid(NewsFilter filter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(filter.getPageNumber(), filter.getPageSize());
    }
}
