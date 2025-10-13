package br.com.fiap.BioPure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<String> handleUsuarioNaoEncontrado(UsuarioNaoEncontrado ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
