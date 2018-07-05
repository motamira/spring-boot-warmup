package application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    public String id;

    private PersonalDetails personalDetails;
    private AccountInformation accountInformation;
    private Contacts contacts;
}
