package br.com.pioneto.controlecontabancaria.domain.customer.exceptions;

public class DuplicatedCustomerException extends Exception {

    public DuplicatedCustomerException(String customerDocto){
        super("Customer already exists with customer docto: " + customerDocto);
    }
}
