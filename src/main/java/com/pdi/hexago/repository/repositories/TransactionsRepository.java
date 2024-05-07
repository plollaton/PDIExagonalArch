package com.pdi.hexago.repository.repositories;

import com.pdi.hexago.repository.entities.AccountEntity;
import com.pdi.hexago.repository.entities.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {

    TransactionsEntity findFirstByAccountIsOrderByTimestampDesc(AccountEntity account);
}
