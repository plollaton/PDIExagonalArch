package com.pdi.hexago.domains.customers.mappers;

import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.repository.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer CustomerEntityToCustomer(CustomerEntity customerEntity);


}
