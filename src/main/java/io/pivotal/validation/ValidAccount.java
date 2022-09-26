package io.pivotal.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AccountValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)
@Documented
public @interface ValidAccount {
    String message() default "";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
