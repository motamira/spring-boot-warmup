package com.example.jsuserregistrationservice;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    MongoTemplate mongoTemplate;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() throws Exception {
        this.mongoTemplate.remove(Query.query(where("accountInformation.userName").is("testuser")), User.class);
    }

    @Test
    public void testGetUsers() throws Exception {
    }

    @Test
    public void testAddUserWithValidPayload() throws Exception {

        String payload = "{\n"
            + "  \"personalDetails\" : {\n"
            + "    \"firstName\" : \"Test\",\n"
            + "    \"lastName\" : \"User\",\n"
            + "    \"age\" : 32\n"
            + "  },\n"
            + "  \"accountInformation\" : {\n"
            + "    \"userName\" : \"testuser\",\n"
            + "    \"password\" : \"P@$$w0rd\"\n"
            + "  },\n"
            + "  \"contacts\" : {\n"
            + "    \"cellPhone\" : \"+201234567899\",\n"
            + "     \"email\" : \"test.user@jumia.com\"\n"
            + "  }\n"
            + "}";

        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content(payload)
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testAddUserWithInvalidPayload() throws Exception {
        String invalidPayload = "{\n"
            + "  \"personalDetails\" : {\n"
            + "    \"firstName\" : \"Test\",\n"
            + "    \"lastName\" : \"User\",\n"
            + "    \"age\" : 32\n"
            + "  },\n"
            + "  \"accountInformation\" : {\n"
            + "    \"userName\" : \"testuser\",\n"
            + "    \"password\" : \"Password\"\n"
            + "  },\n"
            + "  \"contacts\" : {\n"
            + "    \"cellPhone\" : \"0123456789\",\n"
            + "     \"email\" : \"test.user@jumia.com\"\n"
            + "  }\n"
            + "}";

        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content(invalidPayload)
        ).andExpect(status().is4xxClientError());
    }

    public void testAddDuplicateUser() throws Exception {
        String payload = "{\n"
            + "  \"personalDetails\" : {\n"
            + "    \"firstName\" : \"Test\",\n"
            + "    \"lastName\" : \"User\",\n"
            + "    \"age\" : 32\n"
            + "  },\n"
            + "  \"accountInformation\" : {\n"
            + "    \"userName\" : \"testuser\",\n"
            + "    \"password\" : \"P@$$w0rd\"\n"
            + "  },\n"
            + "  \"contacts\" : {\n"
            + "    \"cellPhone\" : \"+201234567899\",\n"
            + "     \"email\" : \"test.user@jumia.com\"\n"
            + "  }\n"
            + "}";

        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content(payload)
        ).andExpect(status().is2xxSuccessful());

        String duplicatePayload = "{\n"
            + "  \"personalDetails\" : {\n"
            + "    \"firstName\" : \"Test\",\n"
            + "    \"lastName\" : \"User\",\n"
            + "    \"age\" : 32\n"
            + "  },\n"
            + "  \"accountInformation\" : {\n"
            + "    \"userName\" : \"testuser\",\n"
            + "    \"password\" : \"P@$$w0rd\"\n"
            + "  },\n"
            + "  \"contacts\" : {\n"
            + "    \"cellPhone\" : \"+201234567899\",\n"
            + "     \"email\" : \"test.user@jumia.com\"\n"
            + "  }\n"
            + "}";

        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content(duplicatePayload)
        ).andExpect(status().is4xxClientError());
    }

}