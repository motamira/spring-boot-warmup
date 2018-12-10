package com.jumia.warmup.jsuserregistrationservice.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@Document
public class Contacts {

    @Id
    private String id;

    private String cellPhone;

    private String email;

    public Contacts() {
    }

    public Contacts(String id, String cellPhone, String email) {
        this.id = id;
        this.cellPhone = cellPhone;
        this.email = email;
    }
}