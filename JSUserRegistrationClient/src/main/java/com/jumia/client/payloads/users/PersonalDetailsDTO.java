package com.jumia.client.payloads.users;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
public class PersonalDetailsDTO {

    @NotNull
    @Size(min = 3, max = 30)
    private String firstName;

    private String lastName;

    @NotNull
    @Min(1)
    private Integer age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonalDetailsDTO() {}

    @Override
    public String toString() {
        return "PersonalDetailsDTO{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", age=" + age +
            '}';
    }
}
