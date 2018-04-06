package com.cucumber.tutorial.cucumber.exception;

public class ItemNotFoundException extends Throwable {

    public ItemNotFoundException(String accountNr) {
        super(accountNr);
    }
}
