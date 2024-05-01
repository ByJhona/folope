package com.byjhona.folope.exception;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIncludeProperties({"sucesso", "status", "mensagem"})
public class RelacaoExisteNoBancoException extends RuntimeException{
    boolean sucesso = false;
    int status = 406;
    String mensagem;
    public RelacaoExisteNoBancoException(Long id){
        this.mensagem = "O objeto com id = " + id + " já existe.";
    }
}
