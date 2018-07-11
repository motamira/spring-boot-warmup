package application.controller;

import application.TestData;
import application.entity.AccountInformation;
import application.entity.Contacts;
import application.entity.PersonalDetails;
import application.entity.User;
import application.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserService userService;

    @Autowired
    private MockUserServiceImpl mockUserService;

    @TestConfiguration
    static class MockUserServiceImplTestConfiguration {

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
        public UserService userService() {
            return new MockUserServiceImpl();
        }
    }

    private MockMvc mockMvc;

    private User userUnderTest;

    private ObjectMapper om = new ObjectMapper();

    private static final String registrationActionUrlTemplate = "/api/users";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        mockUserService.deleteAll();
        createUserUnderTest();
        System.out.println(userService);
    }

    @After
    public void tearDown() {
        mockUserService.deleteAll();
    }

    // TEST HELPERS

    private void createUserUnderTest() {
        AccountInformation inTestAccountInformation = new AccountInformation();
        inTestAccountInformation.setUserName(TestData.User.AccountInformation.USERNAME);
        inTestAccountInformation.setPassword(TestData.User.AccountInformation.PASSWORD);

        Contacts inTestContacts = new Contacts();
        inTestContacts.setEmail(TestData.User.Contacts.EMAIL);
        inTestContacts.setCellPhone(TestData.User.Contacts.PHONE);

        PersonalDetails inTestPersonalDetails = new PersonalDetails();
        inTestPersonalDetails.setAge(TestData.User.PersonalDetails.age);
        inTestPersonalDetails.setFirstName(TestData.User.PersonalDetails.firstName);
        inTestPersonalDetails.setLastName(TestData.User.PersonalDetails.lastName);

        userUnderTest = new User();
        userUnderTest.setAccountInformation(inTestAccountInformation);
        userUnderTest.setContacts(inTestContacts);
        userUnderTest.setPersonalDetails(inTestPersonalDetails);
    }

    private String getUserValueAsJSONString() throws Exception {
        return om.writeValueAsString(userUnderTest);
    }

    // TEST CASES

    @Test
    public void register_whenUserDoesNotExist_returnsUserCreated() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(registrationActionUrlTemplate).content(getUserValueAsJSONString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().string(RegistrationActionResponses.CREATED.statusMessage))
            .andDo((result) -> Assert.assertTrue(mockUserService.exists(userUnderTest)))
            .andReturn();
    }

    @Test
    public void register_withInvalidEmail_returnsBadRequest() throws Exception {
        userUnderTest.getContacts().setEmail("not an email");
        this.mockMvc.perform(MockMvcRequestBuilders.post(registrationActionUrlTemplate).content(getUserValueAsJSONString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().string(RegistrationActionResponses.BAD_REQUEST.statusMessage))
            .andDo((result) -> Assert.assertFalse(mockUserService.exists(userUnderTest)))
            .andReturn();
    }

    @Test
    public void register_whenUserAlreadyExists_returnsConflict() throws Exception {
        this.register_whenUserDoesNotExist_returnsUserCreated();
        this.mockMvc.perform(MockMvcRequestBuilders.post(registrationActionUrlTemplate).content(getUserValueAsJSONString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isConflict())
            .andExpect(MockMvcResultMatchers.content().string(RegistrationActionResponses.CONFLICT.statusMessage))
            .andReturn();
    }
}
