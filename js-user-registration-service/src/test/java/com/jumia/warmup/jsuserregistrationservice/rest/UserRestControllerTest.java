package com.jumia.warmup.jsuserregistrationservice.rest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationservice.providers.UserDTOProvider;
import com.jumia.warmup.jsuserregistrationservice.services.UserRegistrationServiceInterface;
import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRegistrationServiceInterface userRegistrationService;

    UserDTO userDTO;

    public UserRestControllerTest() {

        this.userDTO = UserDTOProvider.getUserDTO();
    }

    @Test
    public void registerUser_expectBadeRequest_firstName_null() throws Exception {

        userDTO.getPersonalDetailsDTO().setFirstName(null);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.EMPTY_FIRST_NAME_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_firstName_empty() throws Exception {

        userDTO.getPersonalDetailsDTO().setFirstName(Constants.EMPTY_FIRST_NAME);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.EMPTY_FIRST_NAME_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_userName_null() throws Exception {

        userDTO.getAccountInformationDTO().setUserName(null);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.EMPTY_USER_NAME_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_userName_empty() throws Exception {

        userDTO.getAccountInformationDTO().setUserName(Constants.EMPTY_USER_NAME);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.EMPTY_USER_NAME_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_userName_lessThan4Chars() throws Exception {

        userDTO.getAccountInformationDTO().setUserName(Constants.INVALID_USER_NAME_LESS_THAN_4_CHARS);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.INVALID_USER_NAME_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_userName_greaterThan10Chars() throws Exception {

        userDTO.getAccountInformationDTO().setUserName(Constants.INVALID_USER_NAME_GREATER_THAN_10_CHARS);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.INVALID_USER_NAME_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_password_null() throws Exception {

        userDTO.getAccountInformationDTO().setPassword(null);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.EMPTY_PASSWORD_ERROR_MESSAGE)));
    }


    @Test
    public void registerUser_expectBadeRequest_password_empty() throws Exception {

        userDTO.getAccountInformationDTO().setPassword(Constants.EMPTY_PASSWORD);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.EMPTY_PASSWORD_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_password_noCapitalCharacters() throws Exception {

        userDTO.getAccountInformationDTO().setPassword(Constants.INVALID_PASSWORD_NO_CAPITAL_CHARS);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.INVALID_PASSWORD_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_password_noNumber() throws Exception {

        userDTO.getAccountInformationDTO().setPassword(Constants.INVALID_PASSWORD_NO_NUMBERS);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.INVALID_PASSWORD_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_email_null() throws Exception {

        userDTO.getContactsDTO().setEmail(null);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.EMPTY_EMAIL_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_email_empty() throws Exception {

        userDTO.getContactsDTO().setEmail(Constants.EMPTY_EMAIL);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.EMPTY_EMAIL_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_expectBadeRequest_email_wrongFormat() throws Exception {

        userDTO.getContactsDTO().setEmail(Constants.INVALID_EMAIL);

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.INVALID_EMAIL_ERROR_MESSAGE)));
    }

    @Test
    public void registerUser_method_not_allowed() throws Exception {

        mvc.perform(put(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void registerUser_created_successfully() throws Exception {

        mvc.perform(post(Constants.API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}