package com.jumia.warmup.jsuserregistrationservice.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@Document
@NoArgsConstructor
public class PersonalDetails {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private int age;

    @Builder
    public PersonalDetails(String id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}