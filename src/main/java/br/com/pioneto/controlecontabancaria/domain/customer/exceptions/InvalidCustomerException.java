package br.com.pioneto.controlecontabancaria.domain.customer.exceptions;

public class InvalidCustomerException extends Exception {

    public InvalidCustomerException(String message){
        super(message);
    }
}
