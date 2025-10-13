package br.com.fiap.BioPure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado(String message) {
        super(message);
    }
}
