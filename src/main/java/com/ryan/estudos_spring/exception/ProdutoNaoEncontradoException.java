package com.ryan.estudos_spring.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{
    ProdutoNaoEncontradoException(){}
    ProdutoNaoEncontradoException(String msg){
        super(msg);
    }

}
