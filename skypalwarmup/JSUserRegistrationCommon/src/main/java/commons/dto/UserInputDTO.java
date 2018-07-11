package commons.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDTO {

    @NotNull
    @Valid
    private PersonalDetailsDTO personalDetails;

    @NotNull
    @Valid
    private AccountInformationDTO accountInformation;

    @NotNull
    @Valid
    private ContactsDTO contacts;
}
