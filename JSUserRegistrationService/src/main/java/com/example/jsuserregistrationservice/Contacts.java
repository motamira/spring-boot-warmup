package com.example.jsuserregistrationservice;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
public class Contacts {

    @Pattern(regexp = "^[+][0-9]{9,}$" , message = "Phone number should contain country code")
    private String cellPhone;

    @NotNull
    @Email
    private String email;

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
