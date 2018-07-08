package application.dto;

import static org.junit.Assert.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
public class UserInputDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @After
    public void tearDown() throws Exception {
        validatorFactory.close();
    }

    @Test
    public void testValidUserInputDTOAccpted() throws Exception {
        AccountInformationDTO inTestAccountInformationDTO = new AccountInformationDTO();
        inTestAccountInformationDTO.setUserName("asdfg");
        inTestAccountInformationDTO.setPassword("Aa1$bbb");
        assertTrue(validator.validate(inTestAccountInformationDTO).isEmpty());

        ContactsDTO inTestContactsDTO = new ContactsDTO();
        inTestContactsDTO.setEmail("email@test.co.uk");
        inTestContactsDTO.setCellPhone("+351 910000000");
        assertTrue(validator.validate(inTestContactsDTO).isEmpty());

        PersonalDetailsDTO inTestPersonalDetailsDTO = new PersonalDetailsDTO();
        inTestPersonalDetailsDTO.setAge("25");
        inTestPersonalDetailsDTO.setFirstName("karim");
        assertTrue(validator.validate(inTestPersonalDetailsDTO).isEmpty());

        UserInputDTO inTest = new UserInputDTO();
        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setAccountInformation(inTestAccountInformationDTO);
        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setContacts(inTestContactsDTO);
        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setPersonalDetails(inTestPersonalDetailsDTO);
        assertTrue(validator.validate(inTest).isEmpty());

        inTestAccountInformationDTO.setPassword("");
        assertFalse(validator.validate(inTest).isEmpty());
    }

}