package com.pdi.hexago.domains.customers.service;

import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.DTOs.CustomerDTO;
import com.pdi.hexago.domains.customers.exceptions.CustomerNotFoundException;
import com.pdi.hexago.domains.customers.exceptions.ExistingCustomerException;

import java.util.List;

public interface ICustomerService {

    Customer createCustomer(CustomerDTO customerDTO) throws ExistingCustomerException;
    List<Customer> getAllCustomers();
    Customer getCustomerByDocumentNumber(String documentNumber) throws CustomerNotFoundException;

}
