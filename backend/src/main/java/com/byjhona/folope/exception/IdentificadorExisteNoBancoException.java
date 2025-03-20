package com.byjhona.folope.exception;

public class IdentificadorExisteNoBancoException extends RuntimeException {

    public IdentificadorExisteNoBancoException() {
        super("O ID está cadastrado.");
    }

    public IdentificadorExisteNoBancoException(String mensagem, Object... argumentos) {
        super(String.format(mensagem, argumentos));
    }

    public IdentificadorExisteNoBancoException(String mensagem) {
        super(mensagem);
    }
}
