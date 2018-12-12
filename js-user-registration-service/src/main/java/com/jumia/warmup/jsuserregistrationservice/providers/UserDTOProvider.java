package com.jumia.warmup.jsuserregistrationservice.providers;

import com.jumia.warmup.jsuserregistrationservice.dtos.AccountInformationDTO;
import com.jumia.warmup.jsuserregistrationservice.dtos.ContactsDTO;
import com.jumia.warmup.jsuserregistrationservice.dtos.PersonalDetailsDTO;
import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import java.io.Serializable;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */
public class UserDTOProvider implements Serializable {

    public static UserDTO getUserDTO() {

        return UserDTO.builder()
            .personalDetailsDTO(
                PersonalDetailsDTO.builder()
                    .firstName(Constants.VALID_FIRST_NAME)
                    .lastName(Constants.VALID_SECOND_NAME)
                    .age(Constants.VALID_AGE)
                    .build())
            .accountInformationDTO(
                AccountInformationDTO.builder()
                    .userName(Constants.VALID_USER_NAME)
                    .password(Constants.VALID_PASSWORD)
                    .build())
            .contactsDTO(
                ContactsDTO.builder()
                    .cellPhone(Constants.VALID_PHONE_NUMBER)
                    .email(Constants.VALID_EMAIL)
                    .build())
            .build();
    }
}