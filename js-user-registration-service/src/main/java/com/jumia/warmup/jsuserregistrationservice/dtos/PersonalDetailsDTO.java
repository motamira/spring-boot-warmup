package com.jumia.warmup.jsuserregistrationservice.dtos;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonalDetailsDTO {

    @NotNull(message = Constants.ErrorMessages.EMPTY_FIRST_NAME)
    @NotEmpty(message = Constants.ErrorMessages.EMPTY_FIRST_NAME)
    private String firstName;

    private String lastName;

    @Max(100)
    private int age;

    public PersonalDetailsDTO() {
    }

    public PersonalDetailsDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

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

    @Override
    public String toString() {
        return "PersonalDetailsDTO{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", age=" + age +
            '}';
    }
}
