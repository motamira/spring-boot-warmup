package com.jumia.warmup.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Personal details dto.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonalDetailsDTO {

    @NotNull(message = "Please enter first name!")
    @NotEmpty(message = "Please enter first name!")
    private String firstName;

    private String lastName;

    @NotNull(message = "Please enter age!")
    @Min(value = 0, message = "Please enter valid age!")
    private Integer age;

    /**
     * Instantiates a new Personal details dto.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param age the age
     */
    @JsonCreator
    public PersonalDetailsDTO(
            @JsonProperty("firstName") final String firstName,
            @JsonProperty("lastName") final String lastName,
            @JsonProperty("age") final Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
