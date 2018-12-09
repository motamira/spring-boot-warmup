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
public class AccountInformationDTOTest {

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
                "userName",
                "",
                1
            },
            {
                "userName",
                "Ahmed Msh Zay El Hag Ahmed",
                2
            },
            {
                "userName",
                ".........",
                1
            },
            {
                "userName",
                "GalalAly",
                0
            },
            {
                "password",
                "galalaly",
                1
            },
            {
                "password",
                "GalalAly",
                1
            },
            {
                "password",
                "GalalAly#",
                0
            },
        });
    }

    @Test
    public void validPropertiesTest() {
        AccountInformationDTO payload = new AccountInformationDTO();

        switch (this.propertyName) {
            case "userName":
                payload.setUserName(this.valueToTest);
                break;
            case "password":
                payload.setPassword(this.valueToTest);
                break;
        }

        Set<ConstraintViolation<AccountInformationDTO>> errors = this.validator.validateProperty(payload, this.propertyName);
        int errorsSize = errors.size();
        Assert.assertEquals(this.expectedResult, errorsSize);
    }
}
