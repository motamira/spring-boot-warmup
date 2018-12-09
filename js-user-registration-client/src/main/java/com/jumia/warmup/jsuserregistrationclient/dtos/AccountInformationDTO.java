package com.jumia.warmup.jsuserregistrationclient.dtos;

import com.jumia.warmup.jsuserregistrationclient.utils.Constants;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountInformationDTO implements Serializable {

    @NotNull(message = Constants.ErrorMessages.EMPTY_USER_NAME)
    @NotEmpty(message = Constants.ErrorMessages.EMPTY_USER_NAME)
    @Pattern(regexp = Constants.Regex.USER_NAME, message = Constants.ErrorMessages.INVALID_USER_NAME)
    private String userName;

    @NotNull(message = Constants.ErrorMessages.EMPTY_PASSWORD)
    @NotEmpty(message = Constants.ErrorMessages.EMPTY_PASSWORD)
    @Pattern(regexp = Constants.Regex.PASSWORD, message = Constants.ErrorMessages.INVALID_PASSWORD)
    private String password;

    public AccountInformationDTO() {
    }

    public AccountInformationDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountInformationDTO{" +
            "userName='" + userName + '\'' +
            '}';
    }
}
