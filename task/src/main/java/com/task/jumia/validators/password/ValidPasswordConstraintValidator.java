package com.task.jumia.validators.password;

import com.google.common.base.Joiner;
import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
public class ValidPasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword input) {
    }

    @Override
    public boolean isValid(String inputPassword, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            new CharacterCharacteristicsRule(3, Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Special, 1)
            ))
        ));

        RuleResult result = validator.validate(new PasswordData(inputPassword));

        if (result.isValid()) {
            return true;
        }

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(
            Joiner.on(",").join(validator.getMessages(result)))
            .addConstraintViolation();

        return false;
    }
}
