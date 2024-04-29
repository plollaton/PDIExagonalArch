package com.pdi.hexago.domains.customers.repository;

import com.pdi.hexago.domains.customers.repository.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    public CustomerEntity findByDocumentNumber(String documentNumber);
}
