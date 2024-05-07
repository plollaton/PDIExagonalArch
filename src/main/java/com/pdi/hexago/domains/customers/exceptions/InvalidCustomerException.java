package com.pdi.hexago.domains.customers.exceptions;

public class InvalidCustomerException extends RuntimeException{
    public InvalidCustomerException(String message){
        super(message);
    }
}
