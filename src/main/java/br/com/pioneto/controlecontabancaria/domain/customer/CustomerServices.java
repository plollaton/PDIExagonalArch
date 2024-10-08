package br.com.pioneto.controlecontabancaria.domain.customer;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.pioneto.controlecontabancaria.api.model.CustomerCreationDto;
import br.com.pioneto.controlecontabancaria.api.model.CustomerDto;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.DuplicatedCustomerException;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.InvalidCustomerException;
import br.com.pioneto.controlecontabancaria.domain.ports.ICustomerServices;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerServices implements ICustomerServices {

    private final CustomerCreationValidator customerCreationValidator;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServices(CustomerCreationValidator customerCreationValidator,
                            CustomerRepository customerRepository, 
                            CustomerMapper customerMapper) {
        this.customerCreationValidator = customerCreationValidator;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto createNewCustomer(CustomerCreationDto customer) throws InvalidCustomerException, DuplicatedCustomerException {
        ValidationResult validationResult = this.customerCreationValidator.validate(customer);
        if (!validationResult.isValid()) {
            throw new InvalidCustomerException(validationResult
                    .getErrors()
                    .stream()
                    .map(br.com.fluentvalidator.context.Error::getMessage)
                    .collect(Collectors.joining(", ")));
        }

        CustomerEntity customerEntity = customerRepository.findByCpf(customer.getCpf());
        if (customerEntity != null) {
            throw new DuplicatedCustomerException(customer.getCpf());
        }

        CustomerEntity c = customerMapper.customerCreationDtoToCustomerEntity(customer);

        customerRepository.save(c);

        return customerMapper.customerEntityToCustomerDto(c);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customer) throws CustomerNotFoundException {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(customer.getId());
        if (!customerEntity.isPresent()) {
            throw new CustomerNotFoundException();
        }

        customerEntity = Optional.ofNullable(customerMapper.customerDtoToCustomerEntity(customer));


        CustomerEntity customerSaved = customerRepository.save(customerEntity.get());

        return customerMapper.customerEntityToCustomerDto(customerSaved);
    }

    @Override
    public List<CustomerDto> findAll() {
        List<CustomerEntity> customers = this.customerRepository.findAll();

        return customers.stream().map(customerMapper::customerEntityToCustomerDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto findByDocto(String customerDocto) throws CustomerNotFoundException {
        CustomerEntity customerEntity = this.customerRepository.findByCpf(customerDocto);
        if (customerEntity == null) {
            throw new CustomerNotFoundException();
        }

        return customerMapper.customerEntityToCustomerDto(customerEntity);
    }

    @Override
    public CustomerDto findById(Integer id) throws CustomerNotFoundException {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(id);
        if (customerEntity.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        return customerMapper.customerEntityToCustomerDto(customerEntity.orElse(null));
    }
}
