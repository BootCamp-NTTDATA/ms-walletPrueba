package com.bootcamp.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Client {
    private String idClient;
    private String numberDocument;
    private String documentType;
    private String name;
    private String phone;
    private String email;
    private String IMEI;
}
