package com.jumia.warmup.jsuserregistrationclient.dtos;

import com.jumia.warmup.jsuserregistrationclient.utils.Constants;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "password")
@NoArgsConstructor
public class AccountInformationDTO implements Serializable {

    @NotEmpty(message = Constants.EMPTY_USER_NAME_ERROR_MESSAGE)
    @Pattern(regexp = Constants.USER_NAME_REGEX, message = Constants.INVALID_USER_NAME_ERROR_MESSAGE)
    private String userName;

    @NotEmpty(message = Constants.EMPTY_PASSWORD_ERROR_MESSAGE)
    @Pattern(regexp = Constants.PASSWORD_REGEX, message = Constants.INVALID_PASSWORD_ERROR_MESSAGE)
    private String password;

    @Builder
    public AccountInformationDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
