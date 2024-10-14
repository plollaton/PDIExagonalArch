package br.com.pioneto.controlecontabancaria.domain.ports;

import br.com.pioneto.controlecontabancaria.api.model.AccountCreationDto;
import br.com.pioneto.controlecontabancaria.api.model.AccountDto;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;

import java.util.List;

public interface IAccountService {

    AccountDto createNewAccount(AccountCreationDto accountCreationDto) throws CustomerNotFoundException;
    AccountDto getAccountById(Integer id);
}
