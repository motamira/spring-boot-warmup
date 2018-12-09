package com.jumia.warmup.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type Personal details.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonalDetails {

    @Field(value = "first_name")
    private String firstName;

    @Field(value = "last_name")
    private String lastName;

    private int age;

    /**
     * Instantiates a new Personal details.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param age the age
     */
    public PersonalDetails(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
