package com.jumia.warmup.jsuserregistrationservice.dtos;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @Valid
    @NotNull(message = Constants.ErrorMessages.EMPTY_ACCOUNT_INFO)
    private AccountInformationDTO accountInformationDTO;

    @Valid
    @NotNull(message = Constants.ErrorMessages.EMPTY_CONTACTS)
    private ContactsDTO contactsDTO;

    @Valid
    @NotNull(message = Constants.ErrorMessages.EMPTY_PERSONAL_DETAILS)
    private PersonalDetailsDTO personalDetailsDTO;

    public UserDTO() {
    }

    public UserDTO(AccountInformationDTO accountInformationDTO, ContactsDTO contactsDTO, PersonalDetailsDTO personalDetailsDTO) {
        this.accountInformationDTO = accountInformationDTO;
        this.contactsDTO = contactsDTO;
        this.personalDetailsDTO = personalDetailsDTO;
    }

    public AccountInformationDTO getAccountInformationDTO() {
        return accountInformationDTO;
    }

    public void setAccountInformationDTO(AccountInformationDTO accountInformationDTO) {
        this.accountInformationDTO = accountInformationDTO;
    }

    public ContactsDTO getContactsDTO() {
        return contactsDTO;
    }

    public void setContactsDTO(ContactsDTO contactsDTO) {
        this.contactsDTO = contactsDTO;
    }

    public PersonalDetailsDTO getPersonalDetailsDTO() {
        return personalDetailsDTO;
    }

    public void setPersonalDetailsDTO(PersonalDetailsDTO personalDetailsDTO) {
        this.personalDetailsDTO = personalDetailsDTO;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "accountInformationDTO=" + accountInformationDTO +
            ", contactsDTO=" + contactsDTO +
            ", personalDetailsDTO=" + personalDetailsDTO +
            '}';
    }
}
