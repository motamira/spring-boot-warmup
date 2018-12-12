package com.jumia.warmup.service;

import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.util.Constants;
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
public class UserService implements UserServiceInterface {

    /**
     * The Logger.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Value(Constants.USER_REGISTRATION_SERVICE_URL)
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
                    LOGGER.error(Constants.ERROR_USER_ALREADY_EXIST);
                    break;
                case BAD_REQUEST:
                    LOGGER.error(Constants.ERROR_User_NOT_VALID);
                    break;
                case INTERNAL_SERVER_ERROR:
                    LOGGER.error(Constants.ERROR_SERIVE_NOT_AVALIABLE);
                    break;
                default:
                    LOGGER.error(Constants.ERROR_SOMETHING_WENT_WRONG);
                    break;
            }
        } catch (Exception exception) {

            LOGGER.error(Constants.ERROR_SOMETHING_WENT_WRONG);
        }
    }
}
