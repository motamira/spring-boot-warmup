package com.example.jsuserregistrationservice;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */

public class AccountInformation {

    @NotNull
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "Username should be between 4 and 10 characters and contain alphanumeric characters "
        + "only")
    @Indexed(unique = true)
    private String userName;

    @NotNull
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{4,}$", message = "Password Should contain at least one "
        + "capital letter, a number and a symbol")
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


}
