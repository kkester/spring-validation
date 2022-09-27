package io.pivotal.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class AccountValidator implements ConstraintValidator<ValidAccount, Account> {
    @Override
    public boolean isValid(Account value, ConstraintValidatorContext context) {
        return isNotBlank(value.getUsername()) || isNotBlank(value.getEmail());
    }
}
