package com.example.jsuserregistrationservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@Document
@CompoundIndexes({
    @CompoundIndex(name = "userName_unique_index", def = "{'userName' : 1, 'unique': true}")
})
public class User
{
    @Id
    private String id;

    @JsonProperty("personalDetails")
    @Valid
    @NotNull
    private PersonalDetails personalDetails;

    @JsonProperty("accountInformation")
    @Valid
    @NotNull
    private AccountInformation accountInformation;

    @JsonProperty("contacts")
    @Valid
    @NotNull
    private Contacts contacts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonalDetails getPersonalDetails() {
        return this.personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public AccountInformation getAccountInformation() {
        return this.accountInformation;
    }

    public void setAccountInformation(AccountInformation accountInformation) {
        this.accountInformation = accountInformation;
    }

    public Contacts getContacts() {
        return this.contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

}
