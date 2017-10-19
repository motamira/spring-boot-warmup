package com.task.jumia.payloads.users;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@RunWith(Parameterized.class)
@SpringBootTest
public class ContactsDTOTest {

    @Parameter(value = 0)
    public String propertyName;

    @Parameter(value = 1)
    public String valueToTest;

    @Parameter(value = 2)
    public int expectedResult;

    private TestContextManager testContextManager;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Before
    public void setUp() throws Exception {
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {
                "email",
                "galal@galal.com",
                0
            },
            {
                "email",
                "galal",
                1
            },
            {
                "email",
                "galal@galal.com.uk",
                0
            },
            {
                "cellPhone",
                "12345",
                1
            },
            {
                "cellPhone",
                "+123456",
                0
            },
        });
    }

    @Test
    public void validPropertiesTest() {
        ContactsDTO payload = new ContactsDTO();
        if (this.propertyName.equals("email")) {
            payload.setEmail(this.valueToTest);
        } else {
            payload.setCellPhone(this.valueToTest);
        }

        Set<ConstraintViolation<ContactsDTO>> errors = this.validator.validateProperty(payload, this.propertyName);
        int errorsSize = errors.size();
        Assert.assertEquals(this.expectedResult, errorsSize);
    }
}
