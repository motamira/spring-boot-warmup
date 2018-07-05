package application.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1)
        );
        PasswordData data = new PasswordData(value);
        RuleResult result = validator.validate(data);
        return result.isValid();
    }
}
