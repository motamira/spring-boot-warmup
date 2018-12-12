package com.jumia.warmup.jsuserregistrationservice.dtos;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ContactsDTO {

    private String cellPhone;

    @NotEmpty(message = Constants.EMPTY_EMAIL_ERROR_MESSAGE)
    @Email(message = Constants.INVALID_EMAIL_ERROR_MESSAGE)
    private String email;

    public ContactsDTO() {
    }

    public ContactsDTO(String cellPhone, String email) {
        this.cellPhone = cellPhone;
        this.email = email;
    }
}
