package com.jumia.warmup.jsuserregistrationservice.integrationTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.jumia.warmup.dto.AccountInformationDTO;
import com.jumia.warmup.dto.ContactsDTO;
import com.jumia.warmup.dto.PersonalDetailsDTO;
import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.responseEntityHandler.ValidationError;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The type User test.
 */
@IfProfileValue(name = AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, value = "it-test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

    private static final String PATH = "/api/users";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    private UserDTO userDTO;
    private HttpHeaders headers;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {

        userDTO = new UserDTO(
                new PersonalDetailsDTO("Mohammed", "Hanfy", 27),
                new AccountInformationDTO("mhanfy", "M_Hanfy_7"),
                new ContactsDTO("+20 1275284823", "mohammed.ahmed.hanfy@gmail.com"));

        headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * After.
     */
    @After
    public void after() {
        mongoTemplate.getDb().drop();
    }

    /**
     * Test 1 register user created.
     */
    @Test
    public void test1_registerUser_created() {

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.POST, entity, Object.class);

        assertEquals("", HttpStatus.CREATED, response.getStatusCode());
    }

    /**
     * Register user method not allowed.
     */
    @Test
    public void registerUser_method_not_allowed() {

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.PUT, entity, Object.class);

        assertEquals("", HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }

    /**
     * Register user bad request.
     */
    @Test
    public void registerUser_bad_request() {

        userDTO.getPersonalDetailsDTO().setFirstName(null);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.POST, entity, ValidationError.class);

        assertEquals("", HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test 2 register user conflict.
     */
    @Test
    public void test2_registerUser_conflict() {

        this.test1_registerUser_created();

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.POST, entity, Object.class);

        assertEquals("", HttpStatus.CONFLICT, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
