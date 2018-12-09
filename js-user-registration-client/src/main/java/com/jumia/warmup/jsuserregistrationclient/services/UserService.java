package com.jumia.warmup.jsuserregistrationclient.services;

import com.jumia.warmup.jsuserregistrationclient.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The type User service.
 */
@Service
public class UserService implements IUserService {

    //    @Value("${user.registration.service.url}")
    private String userRegistrationServiceURL = "http://localhost:8080/api/users";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendUser(final UserDTO userDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

        restTemplate.exchange(
            userRegistrationServiceURL,
            HttpMethod.POST, entity, Object.class);

    }
}
