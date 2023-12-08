package io.pivotal.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidAccount(message = "Email or Username is required")
@CompareDate(before = "startDate", after="endDate", message = "The start date must be before the end date")
public class Account {
    @NotNull(message = "First Name is required")
    private String firstName;
    @NotNull(message = "Last Name is required")
    private String lastName;
    @Past(message = "DOB must be in the past")
    private LocalDate dob;
    @Pattern(regexp = "^(.+)@(\\S+)$")
    private String email;
    private String username;
    private LocalDate startDate;
    private LocalDate endDate;
}
