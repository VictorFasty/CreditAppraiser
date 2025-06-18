package io.github.victorfasty.msCreditAppraiser.domain.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class CardClients {


    private String name;
    private String flag;
    private BigDecimal freeLimit;



}
