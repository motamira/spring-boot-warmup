package com.jumia.warmup.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;

/**
 * The type User.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "users")
public class User {

    @Field(value = "personal_details")
    private PersonalDetails personalDetails;

    @Field(value = "account_information")
    private AccountInformation accountInformation;

    private Contacts contacts;

    /**
     * Instantiates a new User.
     *
     * @param personalDetails the personal details
     * @param accountInformation the account information
     * @param contacts the contacts
     */
    public User(PersonalDetails personalDetails, AccountInformation accountInformation, Contacts contacts) {
        this.personalDetails = personalDetails;
        this.accountInformation = accountInformation;
        this.contacts = contacts;
    }
}