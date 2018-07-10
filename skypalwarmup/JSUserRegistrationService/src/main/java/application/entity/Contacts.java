package application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {

    private String cellPhone;
    private String email;
}
