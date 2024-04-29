package com.pdi.hexago.domains.customers;

public interface ICustomersDAO {

    public Customer getCustomerByDocumentNumber(String documentNumber);
}
