package com.pdi.hexago.domains.accounts.exceptions;

public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(){
        super("Account not found");
    }
}
