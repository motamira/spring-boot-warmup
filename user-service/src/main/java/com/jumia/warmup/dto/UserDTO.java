package com.jumia.warmup.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The type User dto.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

    @Valid
    @NotNull(message = "Please enter personal details!")
    private PersonalDetailsDTO personalDetailsDTO;

    @Valid
    @NotNull(message = "Please enter account information!")
    private AccountInformationDTO accountInformationDTO;

    @Valid
    @NotNull(message = "Please enter contacts!")
    private ContactsDTO contactsDTO;

    /**
     * Instantiates a new User dto.
     *
     * @param personalDetailsDTO the personal details dto
     * @param accountInformationDTO the account information dto
     * @param contactsDTO the contacts dto
     */
    @Valid
    @JsonCreator
    public UserDTO(
            @JsonProperty("personalDetailsDTO") final PersonalDetailsDTO personalDetailsDTO,
            @JsonProperty("accountInformationDTO") final AccountInformationDTO accountInformationDTO,
            @JsonProperty("contactsDTO") final ContactsDTO contactsDTO) {
        this.personalDetailsDTO = personalDetailsDTO;
        this.accountInformationDTO = accountInformationDTO;
        this.contactsDTO = contactsDTO;
    }
}