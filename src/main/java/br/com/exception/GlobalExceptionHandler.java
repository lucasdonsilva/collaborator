package br.com.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import static br.com.components.Messages.ONLY_NUMBERS;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { CollaboratorAlreadyExistsException.class, FieldRequiredException.class, AgeInvalidException.class})
    public ResponseEntity genericBadRequest(Exception e, WebRequest req) {
    	
    	log.error(e.getMessage());
    	
        return ResponseEntity.badRequest().body(buildMapWithMessage(e.getMessage()));
    }
	
    @ExceptionHandler(value = { CollaboratorNotFoundException.class, SectorNotFoundException.class })
    public ResponseEntity genericNotFound(Exception e, WebRequest req) {

        log.error(e.getMessage());
        
        return ResponseEntity.status(NOT_FOUND).body(buildMapWithMessage(e.getMessage()));
    }
    
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity numberFormatBadRequest(Exception e, WebRequest req) {

        log.error(e.getMessage());
        
        return ResponseEntity.badRequest().body(buildMapWithMessage(ONLY_NUMBERS));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }

    private HashMap buildMapWithMessage(String message) {
        return new HashMap(){{put("message", message);}};
    }
}