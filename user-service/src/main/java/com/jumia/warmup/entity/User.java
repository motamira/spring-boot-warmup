package com.jumia.warmup.entity;


import com.jumia.warmup.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type User.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = Constants.USERS)
public class User {

    @Field(value = Constants.PERSONAL_DETAILS)
    private PersonalDetails personalDetails;

    @Field(value = Constants.ACCOUNT_INFORMATION)
    private AccountInformation accountInformation;

    private Contacts contacts;
}