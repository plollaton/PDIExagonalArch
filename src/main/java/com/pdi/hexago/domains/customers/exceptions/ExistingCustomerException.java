package com.pdi.hexago.domains.customers.exceptions;

public class ExistingCustomerException extends Exception{
    public ExistingCustomerException(String msg){
        super(msg);
    }
}
