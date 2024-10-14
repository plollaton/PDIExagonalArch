package br.com.pioneto.controlecontabancaria.domain.account;

import br.com.pioneto.controlecontabancaria.api.model.AccountCreationDto;
import br.com.pioneto.controlecontabancaria.api.model.AccountDto;
import br.com.pioneto.controlecontabancaria.api.model.CustomerDto;
import br.com.pioneto.controlecontabancaria.domain.customer.CustomerEntity;
import br.com.pioneto.controlecontabancaria.domain.customer.CustomerMapper;
import br.com.pioneto.controlecontabancaria.domain.customer.exceptions.CustomerNotFoundException;
import br.com.pioneto.controlecontabancaria.domain.ports.IAccountService;
import br.com.pioneto.controlecontabancaria.domain.ports.ICustomerServices;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class AccountServices implements IAccountService {

    private final ICustomerServices customerServices;
    private final CustomerMapper customerMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServices(ICustomerServices customerServices,
                           CustomerMapper customerMapper,
                           AccountRepository accountRepository,
                           AccountMapper accountMapper) {
        this.customerServices = customerServices;
        this.customerMapper = customerMapper;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto createNewAccount(AccountCreationDto accountCreationDto) throws CustomerNotFoundException {
        CustomerDto customer = this.customerServices.findById(accountCreationDto.getCustomerId());
        AccountEntity account = new AccountEntity();
        account.setNumber("1");
        account.setAccountType(accountCreationDto.getAccountType());
        account.setCreationDate("hj");
        account.setCustomer(this.customerMapper.customerDtoToCustomerEntity(customer));

        this.accountRepository.save(account);

        return this.accountMapper.accountEntityToAccountDto(account);






    }

    @Override
    public AccountDto getAccountById(Integer id) {
        Optional<AccountEntity> accountOp = this.accountRepository.findById(id);
        if (!accountOp.isPresent()) {
           // throw new AccountNotFoundException(id);
        }

        return accountOp.map(this.accountMapper::accountEntityToAccountDto).get();
    }
}
