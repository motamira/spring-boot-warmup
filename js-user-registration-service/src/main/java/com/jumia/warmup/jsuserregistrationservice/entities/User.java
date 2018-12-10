package com.jumia.warmup.jsuserregistrationservice.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@ToString
public class User {

    @Id
    private String id;

    private AccountInformation accountInformation;

    private Contacts contacts;

    private PersonalDetails personalDetails;

    public User() {
    }

    public User(String id, AccountInformation accountInformation, Contacts contacts, PersonalDetails personalDetails) {
        this.id = id;
        this.accountInformation = accountInformation;
        this.contacts = contacts;
        this.personalDetails = personalDetails;
    }
}