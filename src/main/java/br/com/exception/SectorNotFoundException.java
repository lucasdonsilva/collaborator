package br.com.exception;

public class SectorNotFoundException extends RuntimeException{
    public SectorNotFoundException(String message) {
        super(message);
    }
}
