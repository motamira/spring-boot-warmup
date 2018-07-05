package application.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsDTO {
    @Nullable
    private String cellPhone;

    @NotNull
    @Email
    private String email;
}
