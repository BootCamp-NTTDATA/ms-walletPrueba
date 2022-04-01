package com.bootcamp.wallet.dto;

import com.bootcamp.wallet.entity.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    private String id;
    private BigDecimal amount;
    private String paymentType;
    private Client transmitterClient;
    private Client receiverClient;
}
