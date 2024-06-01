package com.byjhona.folope.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProdutorEventos {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void enviarFilmeCurtido(Long idSala, String mensagem){
        kafkaTemplate.send("Sala_" + idSala, mensagem);
    }
}
