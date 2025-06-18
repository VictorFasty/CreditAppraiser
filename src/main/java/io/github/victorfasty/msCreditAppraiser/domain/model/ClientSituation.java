package io.github.victorfasty.msCreditAppraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientSituation {
    private ClientData client;
    private List<CardClients> cards;
}
