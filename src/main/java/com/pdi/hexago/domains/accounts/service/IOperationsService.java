package com.pdi.hexago.domains.accounts.service;

import com.pdi.hexago.domains.accounts.Account;
import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.domains.Investment;
import com.pdi.hexago.domains.accounts.exceptions.AccountAlreadyExistsException;

import java.math.BigDecimal;
import java.util.UUID;

public interface IOperationsService {
    Account getAccountByAccountNumber(String accountNumber);

    AccountDTO createAccount(AccountDTO accountDTO) throws AccountAlreadyExistsException;
    boolean withDraw(String accountNumber, BigDecimal value);
    boolean deposit(Account account, BigDecimal value);
    boolean transferMoney(Account from, Account to, BigDecimal value);
    boolean Investment(Account account, Investment investment, BigDecimal value);
}
