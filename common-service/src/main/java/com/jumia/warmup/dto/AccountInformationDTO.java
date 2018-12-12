package com.jumia.warmup.dto;

import com.jumia.warmup.util.Constants;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@Builder
@ToString(exclude = "password")
public class AccountInformationDTO implements Serializable {

    @NotEmpty(message = Constants.ENTER_USERNAME)
    @Pattern(regexp = Constants.USERNAME_REGEXP, message = Constants.ENTER_VALID_USERNAME)
    private String userName;

    @NotEmpty(message = Constants.ENTER_PASSWORD)
    @Pattern(regexp = Constants.PASSWORD_REGEXP, message = Constants.ENTER_VALID_PASSWORD)
    private String password;
}
