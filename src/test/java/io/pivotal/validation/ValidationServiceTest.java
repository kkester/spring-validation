package io.pivotal.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidationServiceTest {

    @Autowired
    ValidationService validationService;

    @Test
    void saveAccount_whenMissingFields_throwsConstraintViolation() {
        Account account = Account.builder().build();
        ConstraintViolationException error = assertThrows(
            ConstraintViolationException.class, () -> validationService.saveAccount(account));
        assertThat(error.getConstraintViolations()).hasSize(3);
    }

    @Test
    void saveAccount_whenDobIsInTheFuture_throwsConstraintViolation() {
        Account account = Account.builder()
            .username("bbobber")
            .firstName("Bob")
            .lastName("Bobber")
            .dob(LocalDate.now().plusDays(1))
            .build();

        ConstraintViolationException error = assertThrows(
            ConstraintViolationException.class, () -> validationService.saveAccount(account));

        assertThat(error.getConstraintViolations()).hasSize(1);
        assertThat(error.getConstraintViolations().iterator().next().getMessage()).isEqualTo("DOB must be in the past");
    }

    @Test
    void saveAccount_whenStartDateIsAfterEndDate_throwsConstraintViolation() {
        Account account = Account.builder()
            .username("bbobber")
            .firstName("Bob")
            .lastName("Bobber")
            .startDate(LocalDate.now().plusDays(1))
            .endDate(LocalDate.now().minusDays(1))
            .build();

        ConstraintViolationException error = assertThrows(
            ConstraintViolationException.class, () -> validationService.saveAccount(account));

        assertThat(error.getConstraintViolations()).hasSize(1);
        assertThat(error.getConstraintViolations().iterator().next().getMessage()).isEqualTo("The start date must be before the end date");
    }

    @Test
    void validateAccountProgrammatically_whenMissingFields_throwsConstraintViolation() {
        Account account = Account.builder().build();
        ConstraintViolationException error = assertThrows(
            ConstraintViolationException.class,
            () -> validationService.validateAccountProgrammatically(account));
        assertThat(error.getConstraintViolations()).hasSize(3);
    }

    @Test
    void saveAccount_withInvalidEMail_throwsConstraintViolation() {
        Account account = Account.builder()
            .firstName("D")
            .lastName("Boss")
            .email("dboss-hotmail.com")
            .build();
        ConstraintViolationException error = assertThrows(
            ConstraintViolationException.class,
            () -> validationService.validateAccountProgrammatically(account));
        assertThat(error.getConstraintViolations()).hasSize(1);
    }

    @Test
    void saveAccount_usingValidData_isSuccessful() {
        Account account = Account.builder()
            .firstName("D")
            .lastName("Boss")
            .email("dboss@hotmail.com")
            .build();
        validationService.saveAccount(account);
    }

}