package br.com.zup.asgard.asgardbank.controller;

import br.com.zup.asgard.asgardbank.exception.AccountAlreadyExistsException;
import br.com.zup.asgard.asgardbank.exception.AccountNotFoundException;
import br.com.zup.asgard.asgardbank.exception.CustomerNotDeletedException;
import br.com.zup.asgard.asgardbank.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException() {
        return ResponseEntity.notFound().build();

    }
    @ExceptionHandler(CustomerNotDeletedException.class)
    public ResponseEntity<String> handleCustomerNotDeletedException() {
        return ResponseEntity.unprocessableEntity()
                .body("Customer must not be removed");
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<String> handleAccountAlreadyExistsException() {
        return ResponseEntity.unprocessableEntity().body("Account already exists");
    }




}
