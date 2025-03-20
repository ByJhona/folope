package com.byjhona.folope.exception;

public class EmailExisteNoBancoException extends RuntimeException {

    public EmailExisteNoBancoException() {
        super("O usuário está cadastrado.");
    }

    public EmailExisteNoBancoException(String mensagem, Object... argumentos) {
        super(String.format(mensagem, argumentos));
    }

    public EmailExisteNoBancoException(String mensagem) {
        super(mensagem);
    }
}
