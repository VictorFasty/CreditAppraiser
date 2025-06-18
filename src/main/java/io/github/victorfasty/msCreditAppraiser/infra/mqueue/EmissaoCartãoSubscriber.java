package io.github.victorfasty.msCreditAppraiser.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartãoSubscriber {


    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitaçãoEmissao(@Payload String payload){
        System.out.println(payload);
    }
}
