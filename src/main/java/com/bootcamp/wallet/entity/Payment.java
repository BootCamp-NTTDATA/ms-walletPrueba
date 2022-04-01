package com.bootcamp.wallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Payment extends BaseEntity{
    private BigDecimal amount;
    private String paymentType;
    private Client transmitterClient;
    private Client receiverClient;
}
