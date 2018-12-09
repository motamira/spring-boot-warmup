package com.jumia.warmup.jsuserregistrationservice.rest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.warmup.jsuserregistrationservice.dtos.AccountInformationDTO;
import com.jumia.warmup.jsuserregistrationservice.dtos.ContactsDTO;
import com.jumia.warmup.jsuserregistrationservice.dtos.PersonalDetailsDTO;
import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationservice.services.UserRegistrationService;
import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import com.jumia.warmup.jsuserregistrationservice.utils.Constants.ErrorMessages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRegistrationRest.class)
public class UserRegistrationRestTest {

    public static final String API_USERS = "/api/users";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRegistrationService userRegistrationService;

    UserDTO userDTO;

    public UserRegistrationRestTest() {
        this.userDTO = new UserDTO(new AccountInformationDTO("sara", "aaZZa44@")
            , new ContactsDTO("01234774774", "someEmail@gmail.com")
            , new PersonalDetailsDTO("Sara", "Salah", 20));
    }

    @Test
    public void registerUser_expectBadeRequest_firstName_null() throws Exception {

        userDTO.getPersonalDetailsDTO().setFirstName(null);

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.EMPTY_FIRST_NAME)));
    }

    @Test
    public void registerUser_expectBadeRequest_firstName_empty() throws Exception {

        userDTO.getPersonalDetailsDTO().setFirstName("");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.EMPTY_FIRST_NAME)));
    }

    @Test
    public void registerUser_expectBadeRequest_userName_null() throws Exception {

        userDTO.getAccountInformationDTO().setUserName(null);

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.EMPTY_USER_NAME)));
    }

    @Test
    public void registerUser_expectBadeRequest_userName_empty() throws Exception {

        userDTO.getAccountInformationDTO().setUserName("");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.EMPTY_USER_NAME)));
    }

    @Test
    public void registerUser_expectBadeRequest_userName_lessThan4Chars() throws Exception {

        userDTO.getAccountInformationDTO().setUserName("aaa");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.INVALID_USER_NAME)));
    }

    @Test
    public void registerUser_expectBadeRequest_userName_greaterThan10Chars() throws Exception {

        userDTO.getAccountInformationDTO().setUserName("asdhyuikjhdthtr");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.INVALID_USER_NAME)));
    }

    @Test
    public void registerUser_expectBadeRequest_password_null() throws Exception {

        userDTO.getAccountInformationDTO().setPassword(null);

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.EMPTY_PASSWORD)));
    }


    @Test
    public void registerUser_expectBadeRequest_password_empty() throws Exception {

        userDTO.getAccountInformationDTO().setPassword("");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.EMPTY_PASSWORD)));
    }

    @Test
    public void registerUser_expectBadeRequest_password_noCapitalCharacters() throws Exception {

        userDTO.getAccountInformationDTO().setPassword("somepassword12@#");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(ErrorMessages.INVALID_PASSWORD)));
    }

//    @Test
//    public void registerUser_expectBadeRequest_password_noSpecialCharacters() throws Exception {
//
//        userDTO.getAccountInformationDTO().setPassword("somePassword12");
//
//        mvc.perform(post(API_USERS)
//            .contentType(APPLICATION_JSON)
//            .content(asJsonString(userDTO))
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isBadRequest())
//            .andExpect(content().string(containsString(Constants.ErrorMessages.INVALID_PASSWORD)));
//    }

    @Test
    public void registerUser_expectBadeRequest_password_noNumber() throws Exception {

        userDTO.getAccountInformationDTO().setPassword("somePassword$%");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.INVALID_PASSWORD)));
    }

    @Test
    public void registerUser_expectBadeRequest_email_null() throws Exception {

        userDTO.getContactsDTO().setEmail(null);

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.EMPTY_EMAIL)));
    }

    @Test
    public void registerUser_expectBadeRequest_email_empty() throws Exception {

        userDTO.getContactsDTO().setEmail("");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.EMPTY_EMAIL)));
    }

    @Test
    public void registerUser_expectBadeRequest_email_wrongFormat() throws Exception {

        userDTO.getContactsDTO().setEmail("hjjhjjhjh.com");

        mvc.perform(post(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ErrorMessages.INVALID_EMAIL)));
    }

    @Test
    public void registerUser_method_not_allowed() throws Exception {

        mvc.perform(put(API_USERS)
            .contentType(APPLICATION_JSON)
            .content(asJsonString(userDTO))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void registerUser_created_successfully() throws Exception {

        mvc.perform(post(API_USERS)
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