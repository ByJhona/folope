package com.byjhona.folope.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeException {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<NaoEncontradoException> tratarNaoEncontradoException(NaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }
    @ExceptionHandler(NaoAutorizadoException.class)
    public ResponseEntity<NaoAutorizadoException> tratarNaoAutorizadoException(NaoAutorizadoException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex);
    }
    @ExceptionHandler(RelacaoExisteNoBancoException.class)
    public ResponseEntity<RelacaoExisteNoBancoException> tratarRelacaoExisteNoBancoException(RelacaoExisteNoBancoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
    }
}
