package io.pivotal.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
    private String email;
    private String username;
    private LocalDate startDate;
    private LocalDate endDate;
}
