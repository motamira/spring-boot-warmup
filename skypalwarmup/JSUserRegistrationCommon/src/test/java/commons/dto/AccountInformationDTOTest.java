package commons.dto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
public class AccountInformationDTOTest {

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
    public void testUserNameShouldBeBetween4And10Characters() {
        AccountInformationDTO inTest = new AccountInformationDTO();
        inTest.setPassword("Aa1$bbb");

        inTest.setUserName("ak");
        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setUserName("ak123456789");
        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setUserName("ak1234");
        assertTrue(validator.validate(inTest).isEmpty());
    }

    @Test
    public void testValidPasswordAccepted() {
        AccountInformationDTO inTest = new AccountInformationDTO();
        inTest.setUserName("asdfg");

        inTest.setPassword("Aa1$bbb");
        assertTrue(validator.validate(inTest).isEmpty());

        inTest.setPassword("A1$");
        assertTrue(validator.validate(inTest).isEmpty());

        inTest.setPassword("A 1a$^*");
        assertTrue(validator.validate(inTest).isEmpty());
    }

    @Test
    public void testValidPasswordRejected() {
        AccountInformationDTO inTest = new AccountInformationDTO();
        inTest.setUserName("asdfg");

        inTest.setPassword("Aa1bbb");
        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setPassword("A$$");
        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setPassword("A a$^*");
        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setPassword("#");
        assertFalse(validator.validate(inTest).isEmpty());
    }
}
