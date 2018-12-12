package com.jumia.warmup.jsuserregistrationservice.rest;

import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationservice.providers.UserDTOProvider;
import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class UserDTOUnitTest {

    private Validator validator;
    private UserDTO userDTO;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        this.userDTO = UserDTOProvider.getUserDTO();
    }

    @Test
    public void setFirstName_when_null_validationFails() {

        userDTO.getPersonalDetailsDTO().setFirstName(null);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setFirstName_when_empty_validationFail() {

        userDTO.getPersonalDetailsDTO().setFirstName(Constants.EMPTY);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setUserName_when_null_validationFail() {

        userDTO.getAccountInformationDTO().setUserName(null);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setUserName_when_empty_validationFail() {

        userDTO.getAccountInformationDTO().setUserName(Constants.EMPTY);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setUserName_when_lessThan4Chars_validationFail() {

        userDTO.getAccountInformationDTO().setUserName(Constants.INVALID_USER_NAME_LESS_THAN_4_CHARS);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setUserName_when_greaterThan10Chars_validationFail() {

        userDTO.getAccountInformationDTO().setUserName(Constants.INVALID_USER_NAME_GREATER_THAN_10_CHARS);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setPassword_when_null_validationFail() {

        userDTO.getAccountInformationDTO().setPassword(null);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setPassword_when_empty_validationFail() {

        userDTO.getAccountInformationDTO().setPassword(Constants.EMPTY);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setPassword_when_noCapitalCharacters_validationFail() {

        userDTO.getAccountInformationDTO().setPassword(Constants.INVALID_PASSWORD_NO_CAPITAL_CHARS);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setPassword_when_noNumber_validationFail() {

        userDTO.getAccountInformationDTO().setPassword(Constants.INVALID_PASSWORD_NO_NUMBERS);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setEmail_when_null_validationFail() {

        userDTO.getContactsDTO().setEmail(null);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setEmail_when_empty_validationFail() {

        userDTO.getContactsDTO().setEmail(Constants.EMPTY);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void setEmail_when_wrongFormat_validationFail() {

        userDTO.getContactsDTO().setEmail(Constants.INVALID_EMAIL);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void createUser_successfully() {

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        Assert.assertTrue(violations.isEmpty());
    }

}