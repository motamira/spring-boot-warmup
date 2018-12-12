package com.jumia.warmup.provider;

import com.jumia.warmup.dto.AccountInformationDTO;
import com.jumia.warmup.dto.ContactsDTO;
import com.jumia.warmup.dto.PersonalDetailsDTO;
import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.util.Constants;
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
                    .lastName(Constants.VALID_LAST_NAME)
                    .age(Constants.VALID_AGE)
                    .build())
            .accountInformationDTO(
                AccountInformationDTO.builder()
                    .userName(Constants.VALID_USERNAME)
                    .password(Constants.VALID_PASSWORD)
                    .build())
            .contactsDTO(
                ContactsDTO.builder()
                    .cellPhone(Constants.VALID_CELL_PHONE)
                    .email(Constants.VALID_EMAIL)
                    .build())
            .build();
    }
}
