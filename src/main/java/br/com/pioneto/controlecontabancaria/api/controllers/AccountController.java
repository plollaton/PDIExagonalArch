package br.com.pioneto.controlecontabancaria.api.controllers;

import br.com.pioneto.controlecontabancaria.api.interfaces.AccountApi;
import br.com.pioneto.controlecontabancaria.api.model.AccountCreationDto;
import br.com.pioneto.controlecontabancaria.api.model.AccountDto;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;
import br.com.pioneto.controlecontabancaria.domain.ports.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "api")
public class AccountController implements AccountApi {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<AccountDto> postAccount(AccountCreationDto accountCreationDto) throws CustomerNotFoundException {
        AccountDto x = this.accountService.createNewAccount(accountCreationDto);

        return new ResponseEntity<>(x, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountDto> putAccount(AccountDto accountDto) {
        return null;
    }

    @Override
    public ResponseEntity<AccountDto> getAccountById(Integer id) {
        AccountDto account = this.accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountDto> removeAccountById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<AccountDto>> getAccounts(Integer id) {
return null;    }

    @Override
    public ResponseEntity<List<AccountDto>> getAccountsByCustomer(Integer customerId) {
        return null;
    }
}
