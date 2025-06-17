package io.github.victorfasty.msCreditAppraiser.application;


import feign.FeignException;
import io.github.victorfasty.msCreditAppraiser.domain.model.*;
import io.github.victorfasty.msCreditAppraiser.ex.ClientDataNotFoundException;
import io.github.victorfasty.msCreditAppraiser.ex.CommunicationErrorException;
import io.github.victorfasty.msCreditAppraiser.infra.CardsResourceClient;
import io.github.victorfasty.msCreditAppraiser.infra.ClientsResourceClient;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppraiserCreditService {

    private final ClientsResourceClient clientsResourceClient;
    private final CardsResourceClient cardsResourceClient;
    private final CardClients card;

    public ClientSituation FindClientSituation(String cpf) throws ClientDataNotFoundException, CommunicationErrorException{

        try {
            ResponseEntity<ClientData> responseEntity = clientsResourceClient.ClientData(cpf);
            ResponseEntity<List<CardClients>> cardsResponse = cardsResourceClient.getCardsByClient(cpf);

            return ClientSituation.builder()
                    .client(responseEntity.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status ){
                throw new ClientDataNotFoundException();

            }
                throw new CommunicationErrorException(e.getMessage(), status);

        }
    }

    public ReturnAvaliationClient Avaliation(String cpf, Long renda) throws ClientDataNotFoundException, CommunicationErrorException{
        try {

            //Primeiro pega vai no microserviço de cliente e pega  os dados do cliente
            ResponseEntity<ClientData> responseEntity = clientsResourceClient.ClientData(cpf);

            //Depois foi no microserviços de cartões pegar até a renda
            ResponseEntity<List<Card>> cardsResponse = cardsResourceClient.getCardsIncomeLimit(renda);


            //Depois fez a lista de cartões que o cliente pode ter
            List<Card> cards1 = cardsResponse.getBody();
            var listaCartoesAprovados =  cards1.stream().map(card -> {

                //Fez o mapeamento de cada cartão um por um
                ClientData dadosClient = responseEntity.getBody();

                BigDecimal basicLimit = card.getBasiscLimit();
                BigDecimal idadeBD =BigDecimal.valueOf(dadosClient.getIdade());
                var fator =idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limitAprovado = fator.multiply(basicLimit);



                CardsAprove aprove = new CardsAprove();
                aprove.setCard(card.getName());
                aprove.setFlag(card.getFlag());
                aprove.setLimitAprove(limitAprovado);

                return aprove;
            }).collect(Collectors.toList());

        return new ReturnAvaliationClient(listaCartoesAprovados);



        } catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status ){
                throw new ClientDataNotFoundException();

            }
            throw new CommunicationErrorException(e.getMessage(), status);

        }
    }


}
