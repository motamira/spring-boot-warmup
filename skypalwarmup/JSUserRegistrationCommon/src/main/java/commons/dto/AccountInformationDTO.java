package commons.dto;

import commons.dto.validations.ValidPassword;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String userName;

    @NotBlank
    @ValidPassword
    private String password;
}
