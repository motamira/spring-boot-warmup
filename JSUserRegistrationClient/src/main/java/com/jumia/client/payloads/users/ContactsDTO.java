package com.jumia.client.payloads.users;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
public class ContactsDTO {

    @Pattern(regexp = "^\\+{1}[0-9\\s]+$", flags = {Flag.CASE_INSENSITIVE, Flag.CANON_EQ})
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

    public ContactsDTO() {}

    @Override
    public String toString() {
        return "ContactsDTO{" +
            "cellPhone='" + cellPhone + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
