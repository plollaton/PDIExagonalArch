package br.com.pioneto.controlecontabancaria.domain.validators.seeds;

import br.com.pioneto.controlecontabancaria.api.model.CustomerCreationDto;

public class CustomerCreationSeeds {
    public static CustomerCreationDto getEmptyCustomCreation(){
        return new CustomerCreationDto();
    }

    public static CustomerCreationDto getCustomerCreationWithName(){
        CustomerCreationDto customer = new CustomerCreationDto();
        customer.setName("test");

        return customer;
    }

    public static CustomerCreationDto getCustomerCreationWithNameAndEmail(){
        CustomerCreationDto customer = getCustomerCreationWithName();
        customer.setEmail("test@email.com");

        return customer;
    }

    public static CustomerCreationDto getCustomerCreation(){
        CustomerCreationDto customer = getCustomerCreationWithNameAndEmail();
        customer.setCpf("123456789");

        return customer;
    }
}
