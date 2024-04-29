package com.pdi.hexago.domains.accounts.service;

import com.pdi.hexago.domains.accounts.Account;
import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.domains.Investment;
import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.CustomersDAO;
import com.pdi.hexago.domains.accounts.exceptions.AccountAlreadyExistsException;
import com.pdi.hexago.domains.accounts.exceptions.AccountNotFoundException;
import com.pdi.hexago.domains.accounts.mappers.AccountMapper;
import com.pdi.hexago.domains.accounts.repository.AccountRepository;
import com.pdi.hexago.domains.accounts.repository.entities.AccountEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OperationsService implements IOperationsService {

    private AccountRepository accountRepository;

    private CustomersDAO customerDAO;

    public OperationsService(AccountRepository accountRepository,
                             CustomersDAO customerDAO) {
        this.accountRepository = accountRepository;
        this.customerDAO = customerDAO;
    }

    @Override
    public Account getAccountByNumber(String number) {
        AccountEntity accountEntity = accountRepository.findByNumber(number);
        return AccountMapper.INSTANCE.accountEntityToAccount(accountEntity);
    }

    @Override
    public UUID createAccount(AccountDTO accountDTO) throws AccountAlreadyExistsException {
        Account account = getAccountByNumber(accountDTO.accountNumber());
        if (account != null) {
            throw new AccountAlreadyExistsException();
        }

        Customer customer = customerDAO.getCustomerByDocumentNumber(accountDTO.CustomerName());
        if (customer == null) {
            //criar novo custoemr.
        }

       // criar nova conta




        return null;
    }

    @Override
    public boolean withDraw(String accountNumber, BigDecimal value) {
        try {
            Account account = getAccountByNumber(accountNumber);
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
