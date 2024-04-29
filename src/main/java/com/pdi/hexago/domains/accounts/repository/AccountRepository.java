package com.pdi.hexago.domains.accounts.repository;

import com.pdi.hexago.domains.accounts.repository.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByNumber(String accountNumber);
}
