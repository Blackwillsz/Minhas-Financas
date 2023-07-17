package br.com.dzvolve.minhasfinancas.exceptions;

public class ErrorAuthentication extends RuntimeException{

    public ErrorAuthentication(String mensagem){
        super(mensagem);
    }
}
