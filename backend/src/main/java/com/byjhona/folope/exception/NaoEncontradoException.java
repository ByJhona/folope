package com.byjhona.folope.exception;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIncludeProperties({"sucesso", "status", "mensagem"})
public class NaoEncontradoException extends RuntimeException{
    boolean sucesso = false;
    int status = 404;
    String mensagem;
    public NaoEncontradoException(String  mensagem){
        this.mensagem = mensagem;
    }
}
