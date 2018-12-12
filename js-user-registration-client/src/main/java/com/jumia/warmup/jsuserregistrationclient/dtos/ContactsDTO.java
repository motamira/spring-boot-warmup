package com.jumia.warmup.jsuserregistrationclient.dtos;

import com.jumia.warmup.jsuserregistrationclient.utils.Constants;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContactsDTO implements Serializable {

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
