package com.jumia.warmup.dto;

import com.jumia.warmup.util.Constants;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type User dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO implements Serializable {

    @Valid
    @NotNull(message = Constants.ENTER_PERSONAL_DETAILS)
    private PersonalDetailsDTO personalDetailsDTO;

    @Valid
    @NotNull(message = Constants.ENTER_ACCOUNT_INFORMATION)
    private AccountInformationDTO accountInformationDTO;

    @Valid
    @NotNull(message = Constants.ENTER_CONTACTS)
    private ContactsDTO contactsDTO;
}