package com.jumia.warmup.entity;

import com.jumia.warmup.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type Contacts.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contacts {

    @Field(Constants.CELL_PHONE)
    private String cellPhone;

    private String email;
}

