package com.ryan.estudos_spring.exception;


public class CategoriaNaoEncontradaException extends RuntimeException{
    CategoriaNaoEncontradaException(){}

    public CategoriaNaoEncontradaException(String message) {
        super(message);
    }
}
