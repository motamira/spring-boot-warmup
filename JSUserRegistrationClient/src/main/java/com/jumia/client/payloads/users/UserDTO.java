package com.jumia.client.payloads.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @JsonProperty(value = "personalDetails")
    @Valid
    private PersonalDetailsDTO personalDetails;

    @NotNull
    @JsonProperty(value = "accountInformation")
    @Valid
    private AccountInformationDTO accountInformation;

    @NotNull
    @JsonProperty(value = "contacts")
    @Valid
    private ContactsDTO contacts;

    public UserDTO() {
    }

    public UserDTO(PersonalDetailsDTO personalDetails, AccountInformationDTO accountInformation,
        ContactsDTO contacts) {
        this.personalDetails = personalDetails;
        this.accountInformation = accountInformation;
        this.contacts = contacts;
    }

    public PersonalDetailsDTO getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetailsDTO personalDetails) {
        this.personalDetails = personalDetails;
    }

    public AccountInformationDTO getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(AccountInformationDTO accountInformation) {
        this.accountInformation = accountInformation;
    }

    public ContactsDTO getContacts() {
        return contacts;
    }

    public void setContacts(ContactsDTO contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "personalDetails=" + personalDetails +
            ", accountInformation=" + accountInformation +
            ", contacts=" + contacts +
            '}';
    }
}
