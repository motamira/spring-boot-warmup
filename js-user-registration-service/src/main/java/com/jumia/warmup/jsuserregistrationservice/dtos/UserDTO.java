package com.jumia.warmup.jsuserregistrationservice.dtos;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDTO {

    @Valid
    @NotNull(message = Constants.EMPTY_ACCOUNT_INFO_ERROR_MESSAGE)
    private AccountInformationDTO accountInformationDTO;

    @Valid
    @NotNull(message = Constants.EMPTY_CONTACTS_ERROR_MESSAGE)
    private ContactsDTO contactsDTO;

    @Valid
    @NotNull(message = Constants.EMPTY_PERSONAL_DETAILS_ERROR_MESSAGE)
    private PersonalDetailsDTO personalDetailsDTO;

    @Builder
    public UserDTO(
        AccountInformationDTO accountInformationDTO,
        ContactsDTO contactsDTO,
        PersonalDetailsDTO personalDetailsDTO) {
        this.accountInformationDTO = accountInformationDTO;
        this.contactsDTO = contactsDTO;
        this.personalDetailsDTO = personalDetailsDTO;
    }
}
