package br.com.exception;

public class CollaboratorAlreadyExistsException extends RuntimeException{
    public CollaboratorAlreadyExistsException(String message) {
        super(message);
    }
}
