package com.task.jumia.validators.password;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@Documented
@Constraint(validatedBy = ValidPasswordConstraintValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE, TYPE_PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password should contain at least one capital letter, a number and a symbol.";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
