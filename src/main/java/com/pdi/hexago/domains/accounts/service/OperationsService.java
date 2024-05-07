package com.pdi.hexago.domains.accounts.service;

import br.com.fluentvalidator.context.ValidationResult;
import com.pdi.hexago.domains.accounts.Account;
import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.domains.Investment;
import com.pdi.hexago.domains.accounts.DTOs.AmountDTO;
import com.pdi.hexago.domains.accounts.exceptions.AccountInvalidException;
import com.pdi.hexago.repository.repositories.TransactionsRepository;
import com.pdi.hexago.repository.entities.TransactionsEntity;
import com.pdi.hexago.domains.accounts.validations.AccountValidator;
import com.pdi.hexago.domains.customers.Customer;
import com.pdi.hexago.domains.customers.CustomersDAO;
import com.pdi.hexago.domains.accounts.exceptions.AccountAlreadyExistsException;
import com.pdi.hexago.domains.accounts.exceptions.AccountNotFoundException;
import com.pdi.hexago.domains.accounts.mappers.AccountMapper;
import com.pdi.hexago.repository.repositories.AccountRepository;
import com.pdi.hexago.repository.entities.AccountEntity;
import com.pdi.hexago.domains.accounts.DTOs.TransactionDTO;
import com.pdi.hexago.domains.customers.mappers.CustomerMapper;
import com.pdi.hexago.domains.customers.repository.CustomerRepository;
import com.pdi.hexago.domains.customers.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static java.time.LocalTime.now;

@Service
public class OperationsService implements IOperationsService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;
    private final AccountValidator accountValidator;
    private final ICustomerService customerService;
    private final TransactionsRepository transactionRepository;
    private final TransactionsRepository transactionsRepository;
    private AccountRepository accountRepository;

    private CustomersDAO customerDAO;

    public OperationsService(AccountRepository accountRepository,
                             CustomersDAO customerDAO,
                             ICustomerService customerService,
                             CustomerRepository customerRepository,
                             CustomerMapper customerMapper,
                             AccountMapper accountMapper,
                             AccountValidator accountValidator,
                             TransactionsRepository transactionsRepository) {
        this.accountRepository = accountRepository;
        this.customerDAO = customerDAO;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.accountMapper = accountMapper;
        this.accountValidator = accountValidator;
        this.transactionRepository = transactionsRepository;
        this.transactionsRepository = transactionsRepository;
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
           customer = customerService.createCustomer(accountDTO.customer());
           account.setCustomer(customer);
        }

        account.setAccountNumber(accountDTO.accountNumber());
        ValidationResult validationResult = accountValidator.validate(account);
        if(!validationResult.isValid()) {
            throw new AccountInvalidException(validationResult.getErrors().toString());
        }

        AccountEntity accountEntity = accountRepository.save(accountMapper.accountToAccountEntity(account));

        TransactionsEntity initialTransaction = new TransactionsEntity();
        initialTransaction.setPreviousAmount(BigDecimal.ZERO);
        initialTransaction.setTransactionValue(accountDTO.amount() != null ? accountDTO.amount() : BigDecimal.ZERO);
        initialTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        initialTransaction.setAccount(accountEntity);
        transactionsRepository.save(initialTransaction);

        return accountMapper.accountEntityToAccountDTO(accountEntity);
    }

    @Override
    public boolean withDraw(String accountNumber, BigDecimal value) {
        try {
            Account account = getAccountByAccountNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException();
            }
            //account.withDraw(value);
            accountRepository.save(AccountMapper.INSTANCE.accountToAccountEntity(account));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deposit(TransactionDTO transactionDTO) throws AccountNotFoundException {
        Account account = getAccountByAccountNumber(transactionDTO.accountNumber());
        if (account == null){
            throw new AccountNotFoundException();
        }





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

    @Override
    public AmountDTO getAmount(String accountNumber) {
        AccountEntity accountEntity = accountRepository.findByAccountNumber(accountNumber);

        TransactionsEntity transactions = transactionsRepository.findFirstByAccountIsOrderByTimestampDesc(accountEntity);

        return new AmountDTO(accountNumber, transactions.getPreviousAmount().add(transactions.getTransactionValue()));
    }
}
