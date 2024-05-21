package com.pdi.hexago.domains.customers.repository;

import com.pdi.hexago.domains.customers.repository.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    public CustomerEntity findByDocumentNumber(String documentNumber);
}
