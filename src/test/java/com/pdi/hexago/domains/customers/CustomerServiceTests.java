package com.pdi.hexago.domains.customers;

import com.pdi.hexago.domains.customers.mappers.CustomerMapper;
import com.pdi.hexago.domains.customers.repository.CustomerRepository;
import com.pdi.hexago.domains.customers.service.CustomerService;
import com.pdi.hexago.domains.customers.validators.CustomerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;

import static org.mockito.Mockito.mock;

public class CustomerServiceTests {

    @BeforeEach
    public void setup(){

        CustomerRepository cr = mock(CustomerRepository.class);
        CustomerMapper cm = mock(CustomerMapper.class);
        CustomerValidator cv = mock(CustomerValidator.class);

        CustomerService customerService = new CustomerService(cr, cm, cv);
    }

    @ParameterizedTest
    public te(){

    }
}
