package com.bootcamp.wallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Payment extends BaseEntity{
    private String idRegisterWallet;
    private List<Movement> movementList;
}