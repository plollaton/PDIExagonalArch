package com.pdi.hexago.domains.accounts.exceptions;

public class AccountAlreadyExistsException extends Throwable {

    public AccountAlreadyExistsException() {
        super("Account already exists");
    }
}
