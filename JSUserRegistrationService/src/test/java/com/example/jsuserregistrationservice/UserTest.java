package com.example.jsuserregistrationservice;

import static org.junit.Assert.*;

import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private LocalValidatorFactoryBean validator;

    private TestContextManager testContextManager;

    @Test
    public void testFirstNameIsValid() throws Exception {
        // Valid Scenario
        User user = new User();
        PersonalDetails userPersonalDetails = new PersonalDetails();

        userPersonalDetails.setFirstName("Ali");
        userPersonalDetails.setLastName("Dawood");
        userPersonalDetails.setAge(32);

        user.setPersonalDetails(userPersonalDetails);

        Set<ConstraintViolation<PersonalDetails>> result = this.validator.validateProperty(userPersonalDetails,"firstName");

        assertTrue(result.isEmpty());

        //Invalid Scenario firstName is not set
        User invalidUser = new User();
        PersonalDetails invalidUserPersonalDetails = new PersonalDetails();

        invalidUserPersonalDetails.setLastName("Dawood");
        invalidUserPersonalDetails.setAge(32);

        invalidUser.setPersonalDetails(invalidUserPersonalDetails);

        Set<ConstraintViolation<PersonalDetails>> invalidResult = this.validator.validateProperty(invalidUserPersonalDetails,"firstName");

        assertFalse(invalidResult.isEmpty());

    }

    @Test
    public void testAgeIsValid() throws Exception {

        // Valid Scenario
        User user = new User();
        PersonalDetails userPersonalDetails = new PersonalDetails();

        userPersonalDetails.setFirstName("Ali");
        userPersonalDetails.setLastName("Dawood");
        userPersonalDetails.setAge(32);

        user.setPersonalDetails(userPersonalDetails);

        Set<ConstraintViolation<PersonalDetails>> result = this.validator.validateProperty(userPersonalDetails,"age");

        assertTrue(result.isEmpty());

        //Invalid Scenario Age is not set
        User invalidUser = new User();
        PersonalDetails invalidUserPersonalDetails = new PersonalDetails();

        invalidUserPersonalDetails.setFirstName("Ali");
        invalidUserPersonalDetails.setLastName("Dawood");

        invalidUser.setPersonalDetails(invalidUserPersonalDetails);

        Set<ConstraintViolation<PersonalDetails>> invalidResult = this.validator.validateProperty(invalidUserPersonalDetails,"age");

        System.out.println(invalidResult);

        assertFalse(invalidResult.isEmpty());

    }

    @Test
    public void testUsernameIsValid() throws Exception {

        // Valid Scenario
        User user = new User();
        AccountInformation accountInformation = new AccountInformation();

        accountInformation.setUserName("adawood");
        accountInformation.setPassword("P@ssw0rd");

        user.setAccountInformation(accountInformation);

        Set<ConstraintViolation<AccountInformation>> result = this.validator.validateProperty(accountInformation,"userName");

        assertTrue(result.isEmpty());

        // Invalid Scenarios
        User invalidUser = new User();
        AccountInformation invalidAccountInformation = new AccountInformation();

        // Username is not set
        invalidAccountInformation.setPassword("P@ssw0rd");
        user.setAccountInformation(invalidAccountInformation);

        Set<ConstraintViolation<AccountInformation>> invalidResult = this.validator.validateProperty(invalidAccountInformation,"userName");

        assertFalse(invalidResult.isEmpty());

        // Username less than 4 characters
        invalidAccountInformation.setUserName("ad");
        invalidAccountInformation.setPassword("P@ssw0rd");

        user.setAccountInformation(invalidAccountInformation);

        Set<ConstraintViolation<AccountInformation>> invalidResult2 = this.validator.validateProperty(invalidAccountInformation,
            "userName");

        assertFalse(invalidResult2.isEmpty());

        // Username more than 10 characters
        invalidAccountInformation.setUserName("adawooooooood");
        invalidAccountInformation.setPassword("P@ssw0rd");

        user.setAccountInformation(invalidAccountInformation);

        Set<ConstraintViolation<AccountInformation>> invalidResult3 = this.validator.validateProperty(invalidAccountInformation,
            "userName");

        assertFalse(invalidResult3.isEmpty());

        // Username contain special characters
        invalidAccountInformation.setUserName("@daw00d");
        invalidAccountInformation.setPassword("P@ssw0rd");

        user.setAccountInformation(invalidAccountInformation);

        Set<ConstraintViolation<AccountInformation>> invalidResult4 = this.validator.validateProperty(invalidAccountInformation,
            "userName");

        assertFalse(invalidResult4.isEmpty());

    }

    @Test
    public void testPasswordIsValid() throws Exception {

        // Valid Scenario
        User user = new User();
        AccountInformation accountInformation = new AccountInformation();

        accountInformation.setUserName("adawood");
        accountInformation.setPassword("P@ssw0rd");

        user.setAccountInformation(accountInformation);

        Set<ConstraintViolation<AccountInformation>> result = this.validator.validateProperty(accountInformation,"userName");

        assertTrue(result.isEmpty());

        // Invalid Scenarios
        User invalidUser = new User();
        AccountInformation invalidAccountInformation = new AccountInformation();

        // Password does not contain capital letter
        invalidAccountInformation.setUserName("adawood");
        invalidAccountInformation.setPassword("p@ssw0rd");
        user.setAccountInformation(invalidAccountInformation);

        Set<ConstraintViolation<AccountInformation>> invalidResult = this.validator.validateProperty(invalidAccountInformation,"password");

        assertFalse(invalidResult.isEmpty());

        // Password does not contain number
        invalidAccountInformation.setUserName("adawood");
        invalidAccountInformation.setPassword("P@ssword");
        user.setAccountInformation(invalidAccountInformation);

        Set<ConstraintViolation<AccountInformation>> invalidResult2 = this.validator.validateProperty(invalidAccountInformation,
            "password");

        assertFalse(invalidResult2.isEmpty());

        // Password does not contain special character
        invalidAccountInformation.setUserName("adawood");
        invalidAccountInformation.setPassword("Passw0rd");
        user.setAccountInformation(invalidAccountInformation);

        Set<ConstraintViolation<AccountInformation>> invalidResult3 = this.validator.validateProperty(invalidAccountInformation,
            "password");

        assertFalse(invalidResult3.isEmpty());
    }

    @Test
    public void testCellPhoneIsValid() throws Exception {

        // Valid Scenario
        User user = new User();
        Contacts contacts = new Contacts();

        contacts.setCellPhone("+201221574243");
        contacts.setEmail("ali.dawood@jumia.com");

        user.setContacts(contacts);

        Set<ConstraintViolation<Contacts>> result = this.validator.validateProperty(contacts,"cellPhone");

        assertTrue(result.isEmpty());

        // Invalid Scenario no country code
        User invalidUser = new User();
        Contacts invalidContacts = new Contacts();

        invalidContacts.setCellPhone("201221574243");
        invalidContacts.setEmail("ali.dawood@jumia.com");

        invalidUser.setContacts(invalidContacts);

        Set<ConstraintViolation<Contacts>> invalidResult = this.validator.validateProperty(invalidContacts,"cellPhone");

        assertFalse(invalidResult.isEmpty());

        // Invalid Scenario not a number
        invalidContacts.setCellPhone("+2012A1574243");
        invalidContacts.setEmail("ali.dawood@jumia.com");

        invalidUser.setContacts(invalidContacts);

        Set<ConstraintViolation<Contacts>> invalidResult2 = this.validator.validateProperty(invalidContacts,"cellPhone");

        assertFalse(invalidResult2.isEmpty());
    }

    public void testEmailIsValid() throws Exception {

        // Valid Scenario
        User user = new User();
        Contacts contacts = new Contacts();

        contacts.setCellPhone("+201221574243");
        contacts.setEmail("ali.dawood@jumia.com");

        user.setContacts(contacts);

        Set<ConstraintViolation<Contacts>> result = this.validator.validateProperty(contacts,"email");

        assertTrue(result.isEmpty());

        // Invalid Email
        User invalidUser = new User();
        Contacts invalidContacts = new Contacts();

        invalidContacts.setCellPhone("+201221574243");
        invalidContacts.setEmail("ali@jumia");

        invalidUser.setContacts(contacts);

        Set<ConstraintViolation<Contacts>> invalidResult = this.validator.validateProperty(invalidContacts,"email");

        assertFalse(invalidResult.isEmpty());
    }

}