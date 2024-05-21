package com.pdi.hexago.domains.customers;

import com.pdi.hexago.domains.customers.mappers.CustomerMapper;
import com.pdi.hexago.repository.repositories.ICustomerRepository;
import com.pdi.hexago.repository.entities.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomersDAO implements ICustomersDAO {

    private ICustomerRepository ICustomerRepository;

    public CustomersDAO(ICustomerRepository ICustomerRepository) {
        this.ICustomerRepository = ICustomerRepository;
    }

    public Customer getCustomerByDocumentNumber(String documentNumber) {
        CustomerEntity customerEntity = ICustomerRepository.findByDocumentNumber(documentNumber);

        return CustomerMapper.INSTANCE.CustomerEntityToCustomer(customerEntity);
    }
}
