package application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInformation {

    @Indexed(unique = true)
    private String userName;

    private String password;
}
