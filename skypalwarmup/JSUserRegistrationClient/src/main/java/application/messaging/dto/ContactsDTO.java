package application.messaging.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\+\\d+ \\d+$")
    private String cellPhone;

    @NotNull
    @Email
    private String email;
}
