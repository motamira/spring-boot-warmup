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
public class PersonalDetailsDTOTest {

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
    public void testFirstNameNotNull() {
        PersonalDetailsDTO inTest = new PersonalDetailsDTO();
        inTest.setAge("25");

        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setFirstName("karim");
        assertTrue(validator.validate(inTest).isEmpty());
    }

    @Test
    public void testAgeNotNull() {
        PersonalDetailsDTO inTest = new PersonalDetailsDTO();
        inTest.setFirstName("karim");

        assertFalse(validator.validate(inTest).isEmpty());

        inTest.setAge("25");
        assertTrue(validator.validate(inTest).isEmpty());
    }

}
