package com.task.jumia.api;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.task.jumia.payloads.users.UserDTO;
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

    @Autowired
    MongoTemplate mongoTemplate;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        System.out.println(mongoTemplate.getDb().getName());
        this.mongoTemplate.remove(Query.query(where("accountInformation.userName").is("galalusere")), UserDTO.class);
    }

    @Test
    public void testSuccessfulScenario() throws Exception {
        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content("{\n"
                    + "  \"personalDetails\" : {\n"
                    + "    \"firstName\" : \"Galal Aly\",\n"
                    + "    \"lastName\" : \"someName\",\n"
                    + "    \"age\" : \"30\"\n"
                    + "  },\n"
                    + "  \"accountInformation\" : {\n"
                    + "    \"userName\" : \"galalusere\",\n"
                    + "    \"password\" : \"gggG#gggg\"\n"
                    + "  },\n"
                    + "  \"contacts\" : {\n"
                    + "    \"cellPhone\" : \"+000 000000\",\n"
                    + "    \"email\" : \"someEmail@domain.com\"\n"
                    + "  }\n"
                    + "}")
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testFailureEmptyDataScenario() throws Exception {
        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void testFailureWrongDataScenario() throws Exception {
        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content("{\n"
                    + "  \"personalDetails\" : {\n"
                    + "    \"firstName\" : \"Galal Aly\",\n"
                    + "    \"lastName\" : \"someName\",\n"
                    + "    \"age\" : \"30\"\n"
                    + "  },\n"
                    + "  \"accountInformation\" : {\n"
                    + "    \"userName\" : \"someUsere\",\n"
                    + "    \"password\" : \"ggggggg\"\n"
                    + "  },\n"
                    + "  \"contacts\" : {\n"
                    + "    \"cellPhone\" : \"+000 000000\",\n"
                    + "    \"email\" : \"someEmail@domain.com\"\n"
                    + "  }\n"
                    + "}")
        ).andExpect(status().is4xxClientError());

        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content("{\n"
                    + "  \"personalDetails\" : {\n"
                    + "    \"firstName\" : \"\",\n"
                    + "    \"lastName\" : \"someName\",\n"
                    + "    \"age\" : \"30\"\n"
                    + "  },\n"
                    + "  \"accountInformation\" : {\n"
                    + "    \"userName\" : \"someUsere2\",\n"
                    + "    \"password\" : \"gggG#gggg\"\n"
                    + "  },\n"
                    + "  \"contacts\" : {\n"
                    + "    \"cellPhone\" : \"+000 000000\",\n"
                    + "    \"email\" : \"someEmail@domain.com\"\n"
                    + "  }\n"
                    + "}")
        ).andExpect(status().is4xxClientError());
    }

    @Test
    public void testUserNameConflicts() throws Exception {
        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content("{\n"
                    + "  \"personalDetails\" : {\n"
                    + "    \"firstName\" : \"Galal Aly\",\n"
                    + "    \"lastName\" : \"someName\",\n"
                    + "    \"age\" : \"30\"\n"
                    + "  },\n"
                    + "  \"accountInformation\" : {\n"
                    + "    \"userName\" : \"galalusere\",\n"
                    + "    \"password\" : \"gggG#gggg\"\n"
                    + "  },\n"
                    + "  \"contacts\" : {\n"
                    + "    \"cellPhone\" : \"+000 000000\",\n"
                    + "    \"email\" : \"someEmail@domain.com\"\n"
                    + "  }\n"
                    + "}")
        ).andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(
            post("/api/users")
                .contentType("application/json")
                .content("{\n"
                    + "  \"personalDetails\" : {\n"
                    + "    \"firstName\" : \"Galal Aly\",\n"
                    + "    \"lastName\" : \"someName\",\n"
                    + "    \"age\" : \"30\"\n"
                    + "  },\n"
                    + "  \"accountInformation\" : {\n"
                    + "    \"userName\" : \"galalusere\",\n"
                    + "    \"password\" : \"gggG#gggg\"\n"
                    + "  },\n"
                    + "  \"contacts\" : {\n"
                    + "    \"cellPhone\" : \"+000 000000\",\n"
                    + "    \"email\" : \"someEmail@domain.com\"\n"
                    + "  }\n"
                    + "}")
        ).andExpect(status().is4xxClientError());
    }
}
