package io.github.victorfasty.msCreditAppraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardsAprove {
    private String card;
    private String flag;
    private BigDecimal limitAprove;

}
