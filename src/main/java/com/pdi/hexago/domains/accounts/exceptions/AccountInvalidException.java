package com.pdi.hexago.domains.accounts.exceptions;

public class AccountInvalidException extends RuntimeException{
    public AccountInvalidException(String message){
        super(message);
    }
}
