package br.com.dzvolve.minhasfinancas.exceptions;

public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String msg) {
        super(msg);
    }
}
