package com.pdi.hexago.domains.accounts.service;

import br.com.fluentvalidator.context.ValidationResult;
import com.pdi.hexago.domains.accounts.Account;
import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.domains.Investment;
import com.pdi.hexago.domains.accounts.exceptions.AccountInvalidException;
import com.pdi.hexago.domains.accounts.validations.AccountValidator;
import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.CustomersDAO;
import com.pdi.hexago.domains.accounts.exceptions.AccountAlreadyExistsException;
import com.pdi.hexago.domains.accounts.exceptions.AccountNotFoundException;
import com.pdi.hexago.domains.accounts.mappers.AccountMapper;
import com.pdi.hexago.domains.accounts.repository.AccountRepository;
import com.pdi.hexago.domains.accounts.repository.entities.AccountEntity;
import com.pdi.hexago.domains.customers.mappers.CustomerMapper;
import com.pdi.hexago.domains.customers.repository.CustomerRepository;
import com.pdi.hexago.domains.customers.repository.entities.CustomerEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OperationsService implements IOperationsService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;
    private final AccountValidator accountValidator;
    private AccountRepository accountRepository;

    private CustomersDAO customerDAO;

    public OperationsService(AccountRepository accountRepository,
                             CustomersDAO customerDAO,
                             CustomerRepository customerRepository,
                             CustomerMapper customerMapper,
                             AccountMapper accountMapper,
                             AccountValidator accountValidator) {
        this.accountRepository = accountRepository;
        this.customerDAO = customerDAO;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.accountMapper = accountMapper;
        this.accountValidator = accountValidator;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        AccountEntity accountEntity = accountRepository.findByAccountNumber(accountNumber);
        return accountMapper.accountEntityToAccount(accountEntity);
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) throws AccountAlreadyExistsException {
        Account account = getAccountByAccountNumber(accountDTO.accountNumber());
        if (account != null) {
            throw new AccountAlreadyExistsException();
        }
        account = Account.builder().build();

        Customer customer = customerDAO.getCustomerByDocumentNumber(accountDTO.customer().documentNumber());
        if (customer == null) {
           customer = new Customer(accountDTO.customer().name(),
                   accountDTO.customer().email(),
                   accountDTO.customer().documentNumber());

           CustomerEntity customerEntity = customerRepository.save(customerMapper.CustomerToCustomerEntity(customer));
           account.setCustomer(customerMapper.CustomerEntityToCustomer(customerEntity));
        }

        account.setAccountNumber(accountDTO.accountNumber());
        ValidationResult validationResult = accountValidator.validate(account);
        if(!validationResult.isValid()) {
            throw new AccountInvalidException(validationResult.getErrors().toString());
        }

        AccountEntity accountEntity = accountRepository.save(accountMapper.accountToAccountEntity(account));

        return accountMapper.accountEntityToAccountDTO(accountEntity);
    }

    @Override
    public boolean withDraw(String accountNumber, BigDecimal value) {
        try {
            Account account = getAccountByAccountNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException();
            }
            account.withDraw(value);
            accountRepository.save(AccountMapper.INSTANCE.accountToAccountEntity(account));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deposit(Account account, BigDecimal value) {
        return false;
    }

    @Override
    public boolean transferMoney(Account from, Account to, BigDecimal value) {
        return false;
    }

    @Override
    public boolean Investment(Account account, Investment investment, BigDecimal value) {
        return false;
    }
}
