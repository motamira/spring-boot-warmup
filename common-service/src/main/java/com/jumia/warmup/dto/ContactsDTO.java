package com.jumia.warmup.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Contacts dto.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ContactsDTO implements Serializable {

    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Please enter valid cell phone, i.e must contain the country indicative.")
    private String cellPhone;

    @NotNull(message = "Please enter email!")
    @NotEmpty(message = "Please enter email!")
    @Email(message = "Please enter valid email!")
    private String email;

    /**
     * Instantiates a new Contacts dto.
     *
     * @param cellPhone the cell phone
     * @param email the email
     */
    @JsonCreator
    public ContactsDTO(
            @JsonProperty("cellPhone") final String cellPhone,
            @JsonProperty("email") final String email) {
        this.cellPhone = cellPhone;
        this.email = email;
    }
}

