package com.pdi.hexago.domains.accounts.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException() {
        super("Account already exists");
    }
}
