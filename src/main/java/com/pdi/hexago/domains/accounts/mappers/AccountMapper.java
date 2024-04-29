package com.pdi.hexago.domains.accounts.mappers;

import com.pdi.hexago.domains.accounts.Account;
import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.domains.accounts.repository.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper( AccountMapper.class );

    Account accountEntityToAccount(AccountEntity accountEntity);
    AccountEntity accountToAccountEntity(Account account);

    Account accountDTOToAccount(AccountDTO accountDTO);
}
