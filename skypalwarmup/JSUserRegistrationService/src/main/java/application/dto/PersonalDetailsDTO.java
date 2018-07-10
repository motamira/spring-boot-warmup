package application.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.lang.Nullable;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetailsDTO {

    @NotBlank
    private String firstName;

    @Nullable
    private String lastName;

    @NotBlank
    @NumberFormat(style = Style.NUMBER)
    private String age;
}
