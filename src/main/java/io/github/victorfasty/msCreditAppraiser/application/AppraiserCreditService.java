package io.github.victorfasty.msCreditAppraiser.application;


import io.github.victorfasty.msCreditAppraiser.domain.model.CardClients;
import io.github.victorfasty.msCreditAppraiser.domain.model.ClientData;
import io.github.victorfasty.msCreditAppraiser.domain.model.ClientSituation;
import io.github.victorfasty.msCreditAppraiser.infra.CardsResourceClient;
import io.github.victorfasty.msCreditAppraiser.infra.ClientsResourceClient;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppraiserCreditService {

    private final ClientsResourceClient clientsResourceClient;
    private final CardsResourceClient cardsResourceClient;

    public ClientSituation FindClientSituation(String cpf){

       ResponseEntity<ClientData> responseEntity = clientsResourceClient.ClientData(cpf);
        ResponseEntity<List<CardClients>> cardsResponse = cardsResourceClient.getCardsByClient(cpf);

       return ClientSituation.builder()
               .client(responseEntity.getBody())
               .cards(cardsResponse.getBody())
               .build();

    }

}
