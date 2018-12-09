package com.jumia.warmup.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type Account information.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountInformation {

    @Indexed(unique = true)
    @Field(value = "user_name")
    private String userName;

    private String password;

    /**
     * Instantiates a new Account information.
     *
     * @param userName the user name
     * @param password the password
     */
    public AccountInformation(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
