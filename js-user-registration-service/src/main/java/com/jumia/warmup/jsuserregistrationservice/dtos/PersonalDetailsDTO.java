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
@Builder
public class PersonalDetailsDTO {

    @NotEmpty(message = Constants.EMPTY_FIRST_NAME_ERROR_MESSAGE)
    private String firstName;

    private String lastName;

    @Max(100)
    private int age;
}
