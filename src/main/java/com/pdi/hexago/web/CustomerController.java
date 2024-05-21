package com.pdi.hexago.web;

import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.DTOs.CustomerDTO;
import com.pdi.hexago.domains.customers.exceptions.CustomerNotFoundException;
import com.pdi.hexago.domains.customers.exceptions.ExistingCustomerException;
import com.pdi.hexago.domains.customers.mappers.CustomerMapper;
import com.pdi.hexago.domains.customers.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    CustomerController(CustomerService customerService,
                       CustomerMapper customerMapper){
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customer) throws ExistingCustomerException {
        Customer customerEntity = customerService.createCustomer(customer);
        return ResponseEntity.ok(customerEntity);
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> l = customerService.getAllCustomers();

        return ResponseEntity.ok(l);
    }

    @GetMapping(value = "/customer/{documentNumber}")
    public ResponseEntity<Customer> getCustomerByDocumentNumber(@PathVariable String documentNumber) throws CustomerNotFoundException {
        Customer c = customerService.getCustomerByDocumentNumber(documentNumber);

        return ResponseEntity.ok(c);
    }
}
