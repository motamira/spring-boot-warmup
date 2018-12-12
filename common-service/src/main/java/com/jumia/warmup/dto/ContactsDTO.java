package com.jumia.warmup.dto;

import com.jumia.warmup.util.Constants;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Contacts dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ContactsDTO implements Serializable {

    @Pattern(regexp = Constants.CELL_PHONE_REGEXP, message = Constants.ENTER_VALID_CELL_PHONE)
    private String cellPhone;

    @NotEmpty(message = Constants.ENTER_EMAIL)
    @Email(message = Constants.ENTER_VALID_EMAIL)
    private String email;
}

