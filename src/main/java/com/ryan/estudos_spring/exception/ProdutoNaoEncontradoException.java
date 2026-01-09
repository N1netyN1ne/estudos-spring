package com.ryan.estudos_spring.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(){}
    public ProdutoNaoEncontradoException(String msg){
        super(msg);
    }

}
