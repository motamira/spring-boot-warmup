package com.jumia.warmup.unitTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.provider.UserDTOProvider;
import com.jumia.warmup.rest.UserRestController;
import com.jumia.warmup.service.UserService;
import com.jumia.warmup.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * The type User rest test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    private static final String PATH = Constants.USER_REST + Constants.USER_REST_REGISTER_USERS;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDTO userDTO;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {

        userDTO = UserDTOProvider.getUserDTO();

        Mockito.doNothing().when(userService).registerUser(userDTO);
    }

    /**
     * Test valid user dto.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidUserDTO() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    /**
     * Test not valid user dto null personal details.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_personalDetails() throws Exception {

        this.userDTO.setPersonalDetailsDTO(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_PERSONAL_DETAILS)));
    }

    /**
     * Test not valid user dto empty first name.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_empty_firstName() throws Exception {

        this.userDTO.getPersonalDetailsDTO().setFirstName(Constants.EMPTY_STRING);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_FIRST_NAME)));
    }

    /**
     * Test not valid user dto null age.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_age() throws Exception {

        this.userDTO.getPersonalDetailsDTO().setAge(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_AGE)));
    }

    /**
     * Test not valid user dto min age.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_min_age() throws Exception {

        this.userDTO.getPersonalDetailsDTO().setAge(Constants.INVALID_AGE);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_VALID_AGE)));
    }

    /**
     * Test not valid user dto null account information.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_accountInformation() throws Exception {

        this.userDTO.setAccountInformationDTO(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_ACCOUNT_INFORMATION)));
    }

    /**
     * Test not valid user dto empty username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_empty_username() throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName(Constants.EMPTY_STRING);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_USERNAME)));
    }

    /**
     * Test not valid user dto short username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_short_username() throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName(Constants.INVALID_SHORT_USERNAME);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_VALID_USERNAME)));
    }

    /**
     * Test not valid user dto long username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_long_username() throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName(Constants.INVALID_LONG_USERNAME);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_VALID_USERNAME)));
    }

    /**
     * Test not valid user dto invalid username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_invalid_username() throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName(Constants.INVALID_USERNAME);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_VALID_USERNAME)));
    }

    /**
     * Test not valid user dto empty password.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_empty_password() throws Exception {

        this.userDTO.getAccountInformationDTO().setPassword(Constants.EMPTY_STRING);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_PASSWORD)));
    }

    /**
     * Test not valid user dto invalid password.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_invalid_password() throws Exception {

        this.userDTO.getAccountInformationDTO().setPassword(Constants.INVALID_PASSWORD);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_VALID_PASSWORD)));
    }

    /**
     * Test not valid user dto null contacts.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_contacts() throws Exception {

        this.userDTO.setContactsDTO(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_CONTACTS)));
    }

    /**
     * Test not valid user dto invalid cell phone.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_invalid_cellPhone() throws Exception {

        this.userDTO.getContactsDTO().setCellPhone(Constants.INVALID_CELL_PHONE);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_VALID_CELL_PHONE)));
    }

    /**
     * Test not valid user dto empty email.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_empty_email() throws Exception {

        this.userDTO.getContactsDTO().setEmail(Constants.EMPTY_STRING);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_EMAIL)));
    }

    /**
     * Test not valid user dto invalid email.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_invalid_email() throws Exception {

        this.userDTO.getContactsDTO().setEmail(Constants.INVALID_EMAIL);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
            .content(asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString(Constants.ENTER_VALID_EMAIL)));
    }

    private String asJsonString(final Object obj) {
        try {
            objectMapper = new ObjectMapper();
            String jsonContent = objectMapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

