package io.github.victorfasty.msCreditAppraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnAvaliationClient {
    private List<CardsAprove> cards;

}
