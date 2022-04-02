package com.bootcamp.wallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Movement {
    private BigDecimal amount;
    private String type;
    private Client client;
    private LocalDateTime date;
}
