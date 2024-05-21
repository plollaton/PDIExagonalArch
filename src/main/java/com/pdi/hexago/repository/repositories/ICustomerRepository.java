package com.pdi.hexago.repository.repositories;

import com.pdi.hexago.repository.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    public CustomerEntity findByDocumentNumber(String documentNumber);
}
