package com.bootcamp.wallet.dto;

import com.bootcamp.wallet.entity.Movement;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    private String id;
    private String idRegisterWallet;
    private List<Movement> movementList;
}
