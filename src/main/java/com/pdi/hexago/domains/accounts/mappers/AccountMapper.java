package com.pdi.hexago.domains.accounts.mappers;

import com.pdi.hexago.domains.accounts.Account;
import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.repository.entities.AccountEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AccountMapper.class,
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper( AccountMapper.class );

    Account accountEntityToAccount(AccountEntity accountEntity);
    AccountDTO accountEntityToAccountDTO(AccountEntity accountEntity);

    AccountEntity accountToAccountEntity(Account account);

    Account accountDTOToAccount(AccountDTO accountDTO);
}
