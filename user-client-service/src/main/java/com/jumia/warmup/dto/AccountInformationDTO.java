package com.jumia.warmup.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Account information dto.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "password")
public class AccountInformationDTO {

    @NotNull(message = "Please enter username!")
    @NotEmpty(message = "Please enter username!")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,10}$", message = "User name should be between 4 and 10 alphanumeric characters.")
    private String userName;

    @NotNull(message = "Please enter password!")
    @NotEmpty(message = "Please enter password!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*+=-_])(?=\\S+$).{3,}$", message = " Password should contain at least one "
        + "capital letter, a number and a symbol.")
    private String password;

    /**
     * Instantiates a new Account information dto.
     *
     * @param userName the user name
     * @param password the password
     */
    @JsonCreator
    public AccountInformationDTO(
        @JsonProperty("userName") final String userName,
        @JsonProperty("password") final String password) {
        this.userName = userName;
        this.password = password;
    }
}
