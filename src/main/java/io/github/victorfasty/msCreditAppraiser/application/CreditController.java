package io.github.victorfasty.msCreditAppraiser.application;

import io.github.victorfasty.msCreditAppraiser.domain.model.ClientSituation;
import io.github.victorfasty.msCreditAppraiser.ex.ClientDataNotFoundException;
import io.github.victorfasty.msCreditAppraiser.ex.CommunicationErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<?> findPerClientSituation(@RequestParam("cpf") String cpf) {
        try {
            ClientSituation clientSituation = appraiserCreditService.FindClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (ClientDataNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        } catch (CommunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }




}
