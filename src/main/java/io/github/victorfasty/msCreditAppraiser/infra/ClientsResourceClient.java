package io.github.victorfasty.msCreditAppraiser.infra;


import io.github.victorfasty.msCreditAppraiser.domain.model.ClientData;
import io.github.victorfasty.msCreditAppraiser.domain.model.ClientSituation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients", path = "/clients")
public interface ClientsResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<ClientData> ClientData(@RequestParam("cpf") String cpf);

}
