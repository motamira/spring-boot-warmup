package com.jumia.warmup.entity;

import com.jumia.warmup.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type Personal details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonalDetails {

    @Field(value = Constants.FIRST_NAME)
    private String firstName;

    @Field(value = Constants.LAST_NAME)
    private String lastName;

    private int age;
}
