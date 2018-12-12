//package com.jumia.warmup.jsuserregistrationservice.rest;
//
//import static org.junit.Assert.assertEquals;
//
//import com.jumia.warmup.jsuserregistrationservice.dtos.AccountInformationDTO;
//import com.jumia.warmup.jsuserregistrationservice.dtos.ContactsDTO;
//import com.jumia.warmup.jsuserregistrationservice.dtos.PersonalDetailsDTO;
//import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;
//import com.jumia.warmup.jsuserregistrationservice.repositories.UserRepository;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class UserRestControllerIntegrationTest {
//
//    public static final String API_USERS = "/api/users";
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    UserDTO userDTO;
//
//    @Before
//    public void setUp() {
//
//        this.userDTO = new UserDTO(new AccountInformationDTO("saraTest", "aaZZa44@")
//            , new ContactsDTO("01234774774", "saraSalah@gmail.com")
//            , new PersonalDetailsDTO("Sara", "Salah", 20));
//    }
//
//    @Test
//    public void registerUser_1_created() {
//
//        ResponseEntity<UserDTO> responseEntity =
//            restTemplate.postForEntity(API_USERS, userDTO, UserDTO.class);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//    }
//
//    @Test
//    public void registerUser_2_conflict() {
//        ResponseEntity<UserDTO> responseEntity =
//            restTemplate.postForEntity(API_USERS, userDTO, UserDTO.class);
//
//        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
//
//        userRepository.delete(userRepository.findByUserName(userDTO.getAccountInformationDTO().getUserName()));
//    }
//
//    @Test
//    public void registerUser_badRequest() {
//
//        userDTO.getPersonalDetailsDTO().setFirstName(null);
//
//        ResponseEntity<UserDTO> responseEntity =
//            restTemplate.postForEntity(API_USERS, userDTO, UserDTO.class);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//    }
//}