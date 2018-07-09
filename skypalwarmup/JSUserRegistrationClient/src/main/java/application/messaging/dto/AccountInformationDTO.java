package application.messaging.dto;

import application.messaging.dto.validations.ValidPassword;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInformationDTO {

    @NotNull
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String userName;

    @NotNull
    @ValidPassword
    private String password;
}
