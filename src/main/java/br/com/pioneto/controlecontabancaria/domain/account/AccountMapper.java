package br.com.pioneto.controlecontabancaria.domain.account;

import br.com.pioneto.controlecontabancaria.api.model.AccountDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "customerEmail", source = "customer.email")
    AccountDto accountEntityToAccountDto(AccountEntity account);
    List<AccountDto> accountEntityToAccountDtoList(List<AccountEntity> accounts);
}
