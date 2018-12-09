package com.jumia.client.payloads.users;

import com.jumia.client.validators.password.ValidPassword;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
public class AccountInformationDTO {

    @NotNull
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[A-Za-z0-9]*$")
    private String userName;

    @NotNull
    @ValidPassword
    private String password;

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

    public AccountInformationDTO() {}

    @Override
    public String toString() {
        return "AccountInformationDTO{" +
            "userName='" + userName + '\'' +
            ", password='" + "******" + '\'' +
            '}';
    }
}
