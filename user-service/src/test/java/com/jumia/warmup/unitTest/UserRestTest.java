package com.jumia.warmup.unitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.warmup.dto.AccountInformationDTO;
import com.jumia.warmup.dto.ContactsDTO;
import com.jumia.warmup.dto.PersonalDetailsDTO;
import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.rest.UserRest;
import com.jumia.warmup.service.UserService;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type User rest test.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserRest.class)
public class UserRestTest {

    private static final String PATH = "/api/users";

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

        userDTO = new UserDTO(
                new PersonalDetailsDTO("Mohammed", "Hanfy", 27),
                new AccountInformationDTO("mhanfy", "M_Hanfy_7"),
                new ContactsDTO("+20 1275284823", "mohammed.ahmed.hanfy@gmail.com")
        );

        Mockito.doNothing().when(userService).registerUser(userDTO);
    }

    /**
     * Test valid user dto.
     *
     * @throws Exception the exception
     */
    @Test
    public void testValidUserDTO()  throws Exception {

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
    public void testNotValidUserDTO_null_personalDetails()  throws Exception {

        this.userDTO.setPersonalDetailsDTO(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter personal details!")));
    }

    /**
     * Test not valid user dto null first name.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_firstName()  throws Exception {

        this.userDTO.getPersonalDetailsDTO().setFirstName(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter first name!")));
    }

    /**
     * Test not valid user dto empty first name.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_empty_firstName()  throws Exception {

        this.userDTO.getPersonalDetailsDTO().setFirstName("");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter first name!")));
    }

    /**
     * Test not valid user dto null age.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_age()  throws Exception {

        this.userDTO.getPersonalDetailsDTO().setAge(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter age!")));
    }

    /**
     * Test not valid user dto min age.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_min_age()  throws Exception {

        this.userDTO.getPersonalDetailsDTO().setAge(-10);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter valid age!")));
    }

    /**
     * Test not valid user dto null account information.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_accountInformation()  throws Exception {

        this.userDTO.setAccountInformationDTO(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter account information!")));
    }

    /**
     * Test not valid user dto null username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_username()  throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter username!")));
    }

    /**
     * Test not valid user dto empty username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_empty_username()  throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName("");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter username!")));
    }

    /**
     * Test not valid user dto short username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_short_username()  throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName("123");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("User name should be between 4 and 10 alphanumeric characters.")));
    }

    /**
     * Test not valid user dto long username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_long_username()  throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName("123456789012");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("User name should be between 4 and 10 alphanumeric characters.")));
    }

    /**
     * Test not valid user dto invalid username.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_invalid_username()  throws Exception {

        this.userDTO.getAccountInformationDTO().setUserName("1234@asdf");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("User name should be between 4 and 10 alphanumeric characters.")));
    }

    /**
     * Test not valid user dto null password.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_password()  throws Exception {

        this.userDTO.getAccountInformationDTO().setPassword(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter password!")));
    }

    /**
     * Test not valid user dto empty password.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_empty_password()  throws Exception {

        this.userDTO.getAccountInformationDTO().setPassword("");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter password!")));
    }

    /**
     * Test not valid user dto invalid password.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_invalid_password()  throws Exception {

        this.userDTO.getAccountInformationDTO().setPassword("asdfg");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Password should contain at least one capital letter, a number and a symbol.")));
    }

    /**
     * Test not valid user dto null contacts.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_contacts()  throws Exception {

        this.userDTO.setContactsDTO(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter contacts!")));
    }

    /**
     * Test not valid user dto invalid cell phone.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_invalid_cellPhone()  throws Exception {

        this.userDTO.getContactsDTO().setCellPhone("123456789");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter valid cell phone, i.e must contain the country indicative.")));
    }

    /**
     * Test not valid user dto null email.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_null_email()  throws Exception {

        this.userDTO.getContactsDTO().setEmail(null);

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter email!")));
    }

    /**
     * Test not valid user dto empty email.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_empty_email()  throws Exception {

        this.userDTO.getContactsDTO().setEmail("");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter email!")));
    }

    /**
     * Test not valid user dto invalid email.
     *
     * @throws Exception the exception
     */
    @Test
    public void testNotValidUserDTO_invalid_email()  throws Exception {

        this.userDTO.getContactsDTO().setEmail("mohammed");

        this.mockMvc.perform(MockMvcRequestBuilders.post(PATH)
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Please enter valid email!")));
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

