package io.github.victorfasty.msCreditAppraiser.application;

import io.github.victorfasty.msCreditAppraiser.domain.model.ClientSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit")
@RequiredArgsConstructor
@EnableFeignClients
public class CreditController {
    private final AppraiserCreditService appraiserCreditService;

    @GetMapping
    public String status(){
        return "ok";
    }


    @GetMapping(value = "situation-client", params = "cpf")
    public ResponseEntity<ClientSituation> FindPerClientSituation(@RequestParam("cpf") String cpf){
        ClientSituation clientSituation = appraiserCreditService.FindClientSituation(cpf);

        return ResponseEntity.ok(clientSituation);
    }


}
