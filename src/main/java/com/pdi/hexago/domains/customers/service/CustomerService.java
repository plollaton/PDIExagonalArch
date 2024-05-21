package com.pdi.hexago.domains.customers.service;

import br.com.fluentvalidator.context.ValidationResult;
import com.pdi.hexago.domains.customers.exceptions.CustomerNotFoundException;
import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.DTOs.CustomerDTO;
import com.pdi.hexago.domains.customers.exceptions.ExistingCustomerException;
import com.pdi.hexago.domains.customers.exceptions.InvalidCustomerException;
import com.pdi.hexago.domains.customers.mappers.CustomerMapper;
import com.pdi.hexago.domains.customers.repository.ICustomerRepository;
import com.pdi.hexago.domains.customers.repository.entities.CustomerEntity;
import com.pdi.hexago.domains.customers.validators.CustomerValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService implements ICustomerService{

    private final ICustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerValidator customerValidator;

    public CustomerService(ICustomerRepository customerRepository,
                           CustomerMapper customerMapper,
                           CustomerValidator customerValidator){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerValidator = customerValidator;
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) throws ExistingCustomerException {
        CustomerEntity customer = customerRepository.findByDocumentNumber(customerDTO.documentNumber());
        if(customer != null){
            throw new ExistingCustomerException("Customer already exists.");
        }

        customer = CustomerEntity
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

    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();

        return customerEntities.stream().map(c -> customerMapper.CustomerEntityToCustomer(c)).toList();
    }

    @Override
    public Customer getCustomerByDocumentNumber(String documentNumber) throws CustomerNotFoundException {
        CustomerEntity customer = customerRepository.findByDocumentNumber(documentNumber);
        if(customer == null){
            throw new CustomerNotFoundException();
        }

        return customerMapper.CustomerEntityToCustomer(customer);
    }
}
