package io.github.victorfasty.msCreditAppraiser.infra;


import io.github.victorfasty.msCreditAppraiser.domain.model.Card;
import io.github.victorfasty.msCreditAppraiser.domain.model.CardClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value= "mscards", path = "/cards/**")
public interface CardsResourceClient {


    @GetMapping(params = "cpf")
    ResponseEntity<List<CardClients>> getCardsByClient(@RequestParam("cpf") String cpf);


    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeLimit(@RequestParam("income") Long income)

}
