package com.byjhona.folope.exception;

import com.byjhona.folope.domain.error_response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeException {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<NaoEncontradoException> tratarNaoEncontradoException(NaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

    @ExceptionHandler(NaoAutorizadoException.class)
    public ResponseEntity<NaoAutorizadoException> tratarNaoAutorizadoException(NaoAutorizadoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex);
    }

    @ExceptionHandler(RelacaoExisteNoBancoException.class)
    public ResponseEntity<RelacaoExisteNoBancoException> tratarRelacaoExisteNoBancoException(RelacaoExisteNoBancoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
    }

    @ExceptionHandler(IdentificadorExisteNoBancoException.class)
    public ResponseEntity<ErrorResponse> tratarIdentificadorExistenteNoBanco(IdentificadorExisteNoBancoException ex) {
        ErrorResponse resposta = new ErrorResponse("erro", HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }

    @ExceptionHandler(EmailExisteNoBancoException.class)
    public ResponseEntity<ErrorResponse> tratarEmailExistenteNoBanco(EmailExisteNoBancoException ex) {
        ErrorResponse resposta = new ErrorResponse("erro", HttpStatus.CONFLICT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> tratarFalhaAutenticacao(AuthenticationException ex) {
        ErrorResponse resposta = new ErrorResponse("erro", HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resposta);
    }
}
