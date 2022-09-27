package io.pivotal.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

@Service
@Validated
@RequiredArgsConstructor
public class ValidationService {

    private final Validator accountValidator;

    public void saveAccount(@Valid Account account) {
        // save account logic would go here
    }

    public void validateAccountProgrammatically(Account account) {
        Set<ConstraintViolation<Account>> constraintViolations = accountValidator.validate(account);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
