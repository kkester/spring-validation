package io.pivotal.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
