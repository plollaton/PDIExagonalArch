package com.pdi.hexago.domains.customers.service;

import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.DTOs.CustomerDTO;

public interface ICustomerService {
    Customer createCustomer(CustomerDTO customerDTO);

}
