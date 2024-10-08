package br.com.pioneto.controlecontabancaria.domain.customer;

import br.com.pioneto.controlecontabancaria.api.model.CustomerCreationDto;
import br.com.pioneto.controlecontabancaria.api.model.CustomerDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    CustomerEntity customerCreationDtoToCustomerEntity(CustomerCreationDto customer);
    CustomerDto customerEntityToCustomerDto(CustomerEntity customer);
    CustomerEntity customerDtoToCustomerEntity(CustomerDto customerDto);
}
