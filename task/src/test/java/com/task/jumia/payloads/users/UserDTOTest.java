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
public class UserDTOTest {

    @Parameter(value = 0)
    public String propertyName;

    @Parameter(value = 1)
    public String message;

    @Parameter(value = 2)
    public String template;

    @Autowired
    private LocalValidatorFactoryBean validator;

    private TestContextManager testContextManager;

    @Before
    public void setUp() throws Exception {
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {
                "accountInformation",
                "Account Information property validation should be raised but it is not",
                "{javax.validation.constraints.NotNull.message}"
            },
            {
                "contacts",
                "Contacts property validation should be raised but it is not",
                "{javax.validation.constraints.NotNull.message}"
            },
            {
                "personalDetails",
                "Personal Details property validation should be raised but it is not",
                "{javax.validation.constraints.NotNull.message}"
            },
        });
    }

    @Test
    public void testMissingProperties() {
        UserDTO payload = new UserDTO();
        Set<ConstraintViolation<UserDTO>> errors = this.validator.validateProperty(payload, this.propertyName);
        int errorsSize = errors.size();
        Assert.assertTrue(!errors.isEmpty());
        Assert.assertEquals(this.message, 1, errorsSize);

        String error = errors.iterator().next().getMessageTemplate();
        Assert.assertTrue(error.equals(this.template));
    }
}
