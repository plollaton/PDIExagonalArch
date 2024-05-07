package com.pdi.hexago.repository.repositories;

import com.pdi.hexago.repository.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByAccountNumber(String accountNumber);
}
