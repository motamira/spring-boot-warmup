package com.jumia.warmup.jsuserregistrationclient.dtos;

import com.jumia.warmup.jsuserregistrationclient.utils.Constants;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonalDetailsDTO implements Serializable {

    @NotNull(message = Constants.EMPTY_FIRST_NAME_ERROR_MESSAGE)
    @NotEmpty(message = Constants.EMPTY_FIRST_NAME_ERROR_MESSAGE)
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
}
