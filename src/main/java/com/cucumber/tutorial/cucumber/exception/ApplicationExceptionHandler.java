package com.cucumber.tutorial.cucumber.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity operationNotAllowed(){
        return new ResponseEntity(null, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity itemNotFound(){
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

}
