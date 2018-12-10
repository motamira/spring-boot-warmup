package com.jumia.warmup.jsuserregistrationclient.services;

import com.jumia.warmup.jsuserregistrationclient.dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * The type User service.
 */
@Service
public class UserService implements IUserService {

    static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    @Value("${user.registration.service.url}")
    private String userRegistrationServiceURL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendUser(final UserDTO userDTO) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

            restTemplate.exchange(
                userRegistrationServiceURL,
                HttpMethod.POST, entity, Object.class);

        } catch (
            HttpClientErrorException exception) {
            switch (exception.getStatusCode()) {

                case CONFLICT:
                    LOG.info(">>>>>>>>>>>>>>>>>>>>> User Already Exists" + userDTO.toString());

                    break;
                case INTERNAL_SERVER_ERROR:
                    LOG.info(">>>>>>>>>>>>>>>>>>>>> Internal Server Error");

                    break;
                default:
                    LOG.info(">>>>>>>>>>>>>>>>>>>>> Client Server Error");
                    break;
            }

        }
    }
}