package ru.skillbox.rest.newsportal.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;
import ru.skillbox.rest.newsportal.web.model.request.ClientFilter;

public class ClientFilterValidValidator implements ConstraintValidator<ClientFilterValid, ClientFilter> {

    @Override
    public boolean isValid(ClientFilter clientFilter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(clientFilter.getPageNumber(), clientFilter.getPageSize());
    }
}
