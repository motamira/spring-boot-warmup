package com.jumia.warmup.jsuserregistrationservice.dtos;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PersonalDetailsDTO {

    @NotEmpty(message = Constants.EMPTY_FIRST_NAME_ERROR_MESSAGE)
    private String firstName;

    private String lastName;

    @Max(value = Constants.MAX_AGE, message = Constants.INVALID_AGE_ERROR_MESSAGE)
    private int age;

    @Builder
    public PersonalDetailsDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
