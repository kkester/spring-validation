package io.pivotal.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDate;

@Slf4j
public class CompareDateValidator implements ConstraintValidator<CompareDate, Object> {

    private String beforeFieldName;
    private String afterFieldName;

    @Override
    public void initialize(final CompareDate constraintAnnotation) {
        beforeFieldName = constraintAnnotation.before();
        afterFieldName = constraintAnnotation.after();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Field beforeDateField = value.getClass().getDeclaredField(beforeFieldName);
            beforeDateField.setAccessible(true);

            final Field afterDateField = value.getClass().getDeclaredField(afterFieldName);
            afterDateField.setAccessible(true);

            final LocalDate beforeDate = (LocalDate) beforeDateField.get(value);
            final LocalDate afterDate = (LocalDate) afterDateField.get(value);

            return beforeDate == null && afterDate == null || beforeDate != null && beforeDate.isBefore(afterDate);
        } catch (final Exception e) {
            log.error("Error occurred", e);
            return false;
        }
    }
}
