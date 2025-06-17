package io.github.victorfasty.msCreditAppraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Card {
    private Long id;
    private String name;
    private String flag;
    private BigDecimal BasiscLimit;

}
