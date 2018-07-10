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
public class ContactsDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @Before
    public void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @After
    public void tearDown() {
        validatorFactory.close();
    }

    @Test
    public void testValidEmailAccepted() {
        ContactsDTO inTest1 = new ContactsDTO();
        inTest1.setEmail("email@test.co.uk");

        ContactsDTO inTest2 = new ContactsDTO();
        inTest2.setEmail("email.email@test.co.uk");

        ContactsDTO inTest3 = new ContactsDTO();
        inTest3.setEmail("email@test.com");

        ContactsDTO inTest4 = new ContactsDTO();
        inTest4.setEmail("email.email@test.com");

        assertTrue(validator.validate(inTest1).isEmpty());
        assertTrue(validator.validate(inTest2).isEmpty());
        assertTrue(validator.validate(inTest3).isEmpty());
        assertTrue(validator.validate(inTest4).isEmpty());
    }

    @Test
    public void testInvalidEmailRejected() {
        ContactsDTO inTest1 = new ContactsDTO();
        inTest1.setEmail("emailtest.co.uk");

        ContactsDTO inTest2 = new ContactsDTO();
        inTest2.setEmail("email.email@testcouk.");

        assertFalse(validator.validate(inTest1).isEmpty());
        assertFalse(validator.validate(inTest2).isEmpty());
    }

    @Test
    public void testValidPhoneAccepted() {
        ContactsDTO inTest = new ContactsDTO();
        inTest.setEmail("email@test.co.uk");

        inTest.setCellPhone("+351 910000000");
        assertTrue(validator.validate(inTest).isEmpty());
    }
}