package com.pdi.hexago.domains.customers;

import com.pdi.hexago.domains.customers.mappers.CustomerMapper;
import com.pdi.hexago.domains.customers.repository.CustomerRepository;
import com.pdi.hexago.domains.customers.repository.entities.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomersDAO implements ICustomersDAO {

    private CustomerRepository customerRepository;

    public CustomersDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerByDocumentNumber(String documentNumber) {
        CustomerEntity customerEntity = customerRepository.findByDocumentNumber(documentNumber);

        return CustomerMapper.INSTANCE.CustomerEntityToCustomer(customerEntity);
    }
}
