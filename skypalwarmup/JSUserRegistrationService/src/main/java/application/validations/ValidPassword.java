package application.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    /**
     * @return the message in case of invalid password
     */
    String message() default "Invalid Password";

    /**
     * @return the constraint groups
     */
    Class<?>[] groups() default {};

    /**
     * @return the payload of the current password
     */
    Class<? extends Payload>[] payload() default {};

}
