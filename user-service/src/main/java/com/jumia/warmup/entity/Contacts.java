package com.jumia.warmup.entity;

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
@ToString
public class Contacts {

    @Field("cell_phone")
    private String cellPhone;

    private String email;

    /**
     * Instantiates a new Contacts.
     *
     * @param cellPhone the cell phone
     * @param email the email
     */
    public Contacts(String cellPhone, String email) {
        this.cellPhone = cellPhone;
        this.email = email;
    }
}

