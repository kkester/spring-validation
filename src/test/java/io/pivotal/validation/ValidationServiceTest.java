package io.pivotal.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ValidationServiceTest {

    @Autowired
    ValidationService validationService;

    private List<String> extractErrorMessages(ConstraintViolationException error) {
        return error.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
    }

    @Test
    void saveAccount_whenMissingFields_throwsConstraintViolation() {
        Account account = Account.builder().build();

        ConstraintViolationException error = assertThrows(
            ConstraintViolationException.class, () -> validationService.saveAccount(account));

        assertThat(extractErrorMessages(error)).containsExactlyInAnyOrder(
            "First Name is required",
            "Last Name is required",
            "Email or Username is required");
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

        assertThat(extractErrorMessages(error)).containsExactly("DOB must be in the past");
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

        assertThat(extractErrorMessages(error)).containsExactly("The start date must be before the end date");
    }

    @Test
    void validateAccountProgrammatically_whenMissingFields_throwsConstraintViolation() {
        Account account = Account.builder().build();

        ConstraintViolationException error = assertThrows(
            ConstraintViolationException.class,
            () -> validationService.validateAccountProgrammatically(account));

        assertThat(extractErrorMessages(error)).containsExactlyInAnyOrder(
            "First Name is required",
            "Last Name is required",
            "Email or Username is required");
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

        assertThat(extractErrorMessages(error)).containsExactly("must match \"^(.+)@(\\S+)$\"");
    }

    @Test
    void saveAccount_usingValidData_isSuccessful() {
        LocalDate now = LocalDate.now();
        Account account = Account.builder()
            .firstName("D")
            .lastName("Boss")
            .email("dboss@hotmail.com")
            .dob(now.minusYears(10))
            .startDate(now)
            .endDate(now.plusDays(1))
            .build();
        validationService.saveAccount(account);
    }

}