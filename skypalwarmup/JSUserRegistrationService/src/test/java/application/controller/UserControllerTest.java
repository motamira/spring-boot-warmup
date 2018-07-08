package application.controller;

import application.controller.UserController.Result;
import application.dao.UserRepository;
import application.entity.AccountInformation;
import application.entity.Contacts;
import application.entity.PersonalDetails;
import application.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
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
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    private User userUnderTest;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        userRepository.deleteAll();
        createUserUnderTest();
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    private void createUserUnderTest() {
        AccountInformation inTestAccountInformation = new AccountInformation();
        inTestAccountInformation.setUserName("sho9meUae");
        inTestAccountInformation.setPassword("somePas$sw1ord");

        Contacts inTestContacts = new Contacts();
        inTestContacts.setEmail("safgdgom8hgejEfmgailm@ail.com");
        inTestContacts.setCellPhone("+000 000000");

        PersonalDetails inTestPersonalDetails = new PersonalDetails();
        inTestPersonalDetails.setAge("30");
        inTestPersonalDetails.setFirstName("someNjame");
        inTestPersonalDetails.setLastName("someName");

        userUnderTest = new User();
        userUnderTest.setAccountInformation(inTestAccountInformation);
        userUnderTest.setContacts(inTestContacts);
        userUnderTest.setPersonalDetails(inTestPersonalDetails);
    }

    private String getUserValueAsJSONString() throws Exception {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(userUnderTest);
    }

    @Test
    public void testRegisterCREATED() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users").content(getUserValueAsJSONString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().string(Result.CREATED.getResponseBody()))
            .andDo((result) -> Assert.assertTrue(userRepository.findOne(Example.of(userUnderTest)).isPresent()))
            .andReturn();
    }

    @Test
    public void testRegisterBAD_REQUEST() throws Exception {
        String oldEmail = userUnderTest.getContacts().getEmail();
        userUnderTest.getContacts().setEmail("not an email");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users").content(getUserValueAsJSONString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.content().string(Result.BAD_REQUEST.getResponseBody()))
            .andReturn();
        userUnderTest.getContacts().setEmail("not an email");
        userUnderTest.getContacts().setEmail(oldEmail);
    }

    @Test
    public void testRegisterCONFLICT() throws Exception {
        this.testRegisterCREATED();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users").content(getUserValueAsJSONString())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isConflict())
            .andExpect(MockMvcResultMatchers.content().string(Result.CONFLICT.getResponseBody()))
            .andReturn();
    }
}