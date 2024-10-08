package br.com.pioneto.controlecontabancaria.domain.ports;

import br.com.pioneto.controlecontabancaria.api.model.CustomerCreationDto;
import br.com.pioneto.controlecontabancaria.api.model.CustomerDto;
import br.com.pioneto.controlecontabancaria.domain.customer.CustomerEntity;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.DuplicatedCustomerException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.InvalidCustomerException;

import java.util.List;

public interface ICustomerServices {

    CustomerDto createNewCustomer(CustomerCreationDto customer) throws InvalidCustomerException, DuplicatedCustomerException;

    List<CustomerDto> findAll();
    CustomerDto findByDocto(String customerDocto) throws CustomerNotFoundException;
    CustomerDto findById(Integer id) throws CustomerNotFoundException;

    CustomerDto updateCustomer(CustomerDto customer) throws CustomerNotFoundException;
}
