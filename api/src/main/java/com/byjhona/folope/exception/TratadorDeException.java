package com.byjhona.folope.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeException {

    @ExceptionHandler(FilmeNaoEncontradoException.class)
    public ResponseEntity tratarFilmeNaoEncontradoException(FilmeNaoEncontradoException ex){
        return ResponseEntity.notFound().build();
    }
}
