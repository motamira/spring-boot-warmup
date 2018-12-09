package com.jumia.warmup.jsuserregistrationservice.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@Document
public class PersonalDetails {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private int age;

    public PersonalDetails() {
    }

    public PersonalDetails(String id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}