package com.ryan.estudos_spring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<String> handleProdutoNaoEncontrado(ProdutoNaoEncontradoException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
