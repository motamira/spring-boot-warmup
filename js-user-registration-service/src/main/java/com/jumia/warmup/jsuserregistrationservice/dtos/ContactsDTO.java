package com.jumia.warmup.jsuserregistrationservice.dtos;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContactsDTO {

    private String cellPhone;

    @NotNull(message = Constants.ErrorMessages.EMPTY_EMAIL)
    @NotEmpty(message = Constants.ErrorMessages.EMPTY_EMAIL)
    @Email(message = Constants.ErrorMessages.INVALID_EMAIL)
    private String email;

    public ContactsDTO() {
    }

    public ContactsDTO(String cellPhone, String email) {
        this.cellPhone = cellPhone;
        this.email = email;
    }

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

    @Override
    public String toString() {
        return "ContactsDTO{" +
            "cellPhone='" + cellPhone + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
