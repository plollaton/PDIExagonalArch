package com.pdi.hexago.domains.customers.mappers;

import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.repository.entities.CustomerEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CustomerMapper.class,
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer CustomerEntityToCustomer(CustomerEntity customerEntity);

    CustomerEntity CustomerToCustomerEntity(Customer customer);
}
