package com.pdi.hexago.domains.customers.service;

import br.com.fluentvalidator.context.ValidationResult;
import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.DTOs.CustomerDTO;
import com.pdi.hexago.domains.customers.exceptions.InvalidCustomerException;
import com.pdi.hexago.domains.customers.mappers.CustomerMapper;
import com.pdi.hexago.domains.customers.repository.CustomerRepository;
import com.pdi.hexago.domains.customers.repository.entities.CustomerEntity;
import com.pdi.hexago.domains.customers.validators.CustomerValidator;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerValidator customerValidator;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper customerMapper,
                           CustomerValidator customerValidator){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerValidator = customerValidator;
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        CustomerEntity customer = CustomerEntity
                .builder()
                .name(customerDTO.name())
                .email(customerDTO.email())
                .documentNumber(customerDTO.documentNumber())
                .build();
        ValidationResult cvr = customerValidator.validate(customerMapper.CustomerEntityToCustomer(customer));
        if(!cvr.isValid()){
            throw new InvalidCustomerException(cvr.getErrors().toString());
        }

        customerRepository.save(customer);
        return customerMapper.CustomerEntityToCustomer(customer);
    }
}
