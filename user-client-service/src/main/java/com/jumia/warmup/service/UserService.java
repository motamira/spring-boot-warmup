package com.jumia.warmup.service;

import com.jumia.warmup.Listener.UserRegistrationListener;
import com.jumia.warmup.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * The type User service.
 */
@Service
public class UserService implements IUserService {

    /**
     * The Logger.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Value("${user.registration.service.url}")
    private String userRegistrationServiceURL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void registerUser(final UserDTO userDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<UserDTO> entity = new HttpEntity<>(userDTO, headers);

        try {
            restTemplate.exchange(
                userRegistrationServiceURL,
                HttpMethod.POST, entity, Object.class);

        } catch (HttpClientErrorException exception) {

            switch (exception.getStatusCode()) {

                case CONFLICT:
                    LOGGER.error("User already exist.");
                    break;
                case BAD_REQUEST:
                    LOGGER.error("User is not valid.");
                    break;
                case INTERNAL_SERVER_ERROR:
                    LOGGER.error("Serive not avaliable.");
                    break;
                default:
                    LOGGER.error("Something went wrong.");
                    break;
            }
        } catch (Exception exception) {

            LOGGER.error("Something went wrong.");
        }
    }
}
