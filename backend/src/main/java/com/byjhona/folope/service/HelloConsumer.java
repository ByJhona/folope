package com.byjhona.folope.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HelloConsumer {
    @KafkaListener(topics = "Sala_10", groupId = "sala-group")
    void receberMensagem(String mensagem){
        System.out.println("Mensagem do consumidor " + mensagem);
    }
}
