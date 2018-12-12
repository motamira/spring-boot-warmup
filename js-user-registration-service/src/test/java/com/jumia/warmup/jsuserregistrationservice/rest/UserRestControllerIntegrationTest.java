package com.jumia.warmup.jsuserregistrationservice.rest;

import static org.junit.Assert.assertEquals;

import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationservice.providers.UserDTOProvider;
import com.jumia.warmup.jsuserregistrationservice.repositories.UserRepository;
import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRestControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    UserDTO userDTO;

    @Before
    public void setUp() {

        this.userDTO = UserDTOProvider.getUserDTO();
    }

    @Test
    public void registerUser_whenCreateUser_return_201() {

        ResponseEntity<UserDTO> responseEntity =
            restTemplate.postForEntity(Constants.API_USERS, userDTO, UserDTO.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void registerUser_whenCreateUser_return_409() {
        ResponseEntity<UserDTO> responseEntity =
            restTemplate.postForEntity(Constants.API_USERS, userDTO, UserDTO.class);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

        userRepository.delete(userRepository.findByUserName(userDTO.getAccountInformationDTO().getUserName()));
    }

    @Test
    public void registerUser_whenValidationFails_return_400() {

        userDTO.getPersonalDetailsDTO().setFirstName(null);

        ResponseEntity<UserDTO> responseEntity =
            restTemplate.postForEntity(Constants.API_USERS, userDTO, UserDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}