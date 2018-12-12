package com.jumia.warmup.jsuserregistrationclient.dtos;

import com.jumia.warmup.jsuserregistrationclient.utils.Constants;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO implements Serializable {

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
    public UserDTO(AccountInformationDTO accountInformationDTO, ContactsDTO contactsDTO, PersonalDetailsDTO personalDetailsDTO) {
        this.accountInformationDTO = accountInformationDTO;
        this.contactsDTO = contactsDTO;
        this.personalDetailsDTO = personalDetailsDTO;
    }
}
