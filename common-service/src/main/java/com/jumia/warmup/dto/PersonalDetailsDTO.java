package com.jumia.warmup.dto;

import com.jumia.warmup.util.Constants;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Personal details dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonalDetailsDTO implements Serializable {

    @NotEmpty(message = Constants.ENTER_FIRST_NAME)
    private String firstName;

    private String lastName;

    @NotNull(message = Constants.ENTER_AGE)
    @Min(value = 0, message = Constants.ENTER_VALID_AGE)
    private Integer age;
}
