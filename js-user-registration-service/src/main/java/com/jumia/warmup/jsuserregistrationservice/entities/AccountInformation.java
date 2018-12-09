package com.jumia.warmup.jsuserregistrationservice.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString(exclude = "password")
@Document
public class AccountInformation {

    @Id
    private String id;

    @Indexed(name = "user_name_index", unique = true)
    private String userName;

    private String password;

    public AccountInformation() {
    }

    public AccountInformation(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
