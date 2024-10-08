package br.com.pioneto.controlecontabancaria.api.controllers;

import br.com.pioneto.controlecontabancaria.api.interfaces.CustomerApi;
import br.com.pioneto.controlecontabancaria.api.model.CustomerDto;
import br.com.pioneto.controlecontabancaria.api.model.CustomerCreationDto;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.DuplicatedCustomerException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.InvalidCustomerException;
import br.com.pioneto.controlecontabancaria.domain.ports.ICustomerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/api")
public class CustomerController implements CustomerApi {

    private final ICustomerServices customerServices;

    public CustomerController(ICustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @Override
    public ResponseEntity<CustomerDto> postCustomer(CustomerCreationDto customer)
            throws InvalidCustomerException, DuplicatedCustomerException {
        CustomerDto customerDto = this.customerServices.createNewCustomer(customer);
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerDto> putAccount(CustomerDto customer) throws CustomerNotFoundException {
        CustomerDto customerDto = this.customerServices.updateCustomer(customer);
        return new ResponseEntity<>(customerDto, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<CustomerDto> getAccount(Integer customerId, String customerDocto) throws CustomerNotFoundException {
        CustomerDto customerDto = new CustomerDto();
        if (customerDocto != null){
            return new ResponseEntity<>(this.customerServices.findByDocto(customerDocto), HttpStatus.ACCEPTED);
        }

        if (customerId != null){
            return new ResponseEntity<>(this.customerServices.findById(customerId), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity deleteAccount(Integer customerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return new ResponseEntity<>(customerServices.findAll(), HttpStatus.OK);
    }
}
