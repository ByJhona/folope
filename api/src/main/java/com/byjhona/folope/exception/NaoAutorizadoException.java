package com.byjhona.folope.exception;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIncludeProperties({"sucesso", "status", "mensagem"})
public class NaoAutorizadoException extends RuntimeException {
    boolean sucesso = false;
    int status = 401;
    String mensagem;

    public NaoAutorizadoException(String mensagem) {
        this.mensagem = mensagem;
    }
}
