package io.pivotal.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidationServiceTest {

    @Autowired
    ValidationService validationService;

    @Test
    void validateAccount_whenMissingFields_throwsConstraintViolation() {
        Account account = Account.builder().build();
        ConstraintViolationException error = assertThrows(ConstraintViolationException.class, () -> {
            validationService.validateAccount(account);
        });

        assertThat(error.getConstraintViolations()).hasSize(3);
    }

    @Test
    void validateAccountProgrammatically_whenMissingFields_throwsConstraintViolation() {
        Account account = Account.builder().build();
        ConstraintViolationException error = assertThrows(ConstraintViolationException.class, () -> {
            validationService.validateAccountProgrammatically(account);
        });

        assertThat(error.getConstraintViolations()).hasSize(3);
    }

}