package com.jumia.warmup.entity;

import com.jumia.warmup.util.Constants;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ToString
public class AccountInformation {

    @Indexed(unique = true)
    @Field(value = Constants.USER_NAME)
    private String userName;

    private String password;
}
