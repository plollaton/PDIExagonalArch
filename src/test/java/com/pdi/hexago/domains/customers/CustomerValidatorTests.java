package com.pdi.hexago.domains.customers;


import com.pdi.hexago.domains.customers.validators.CustomerValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = CustomerValidator.class)
public class CustomerValidatorTests {

    @Autowired
    CustomerValidator customerValidator;

    @ParameterizedTest("1")
    public tes(){

    }
}
