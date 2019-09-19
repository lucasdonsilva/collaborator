package br.com.exception;

public class AgeInvalidException extends RuntimeException{
    public AgeInvalidException(String message) {
        super(message);
    }
}