package io.github.victorfasty.msCreditAppraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardClients {


    private String name;
    private String flag;
    private BigDecimal freeLimit;



}
