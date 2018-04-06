package com.cucumber.tutorial.cucumber.exception;

public class OperationNotAllowedException extends Throwable {

    public OperationNotAllowedException(String message) {
        super(message);
    }
}
