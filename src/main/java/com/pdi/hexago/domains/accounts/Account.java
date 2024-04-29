package com.pdi.hexago.domains.accounts;

import com.pdi.hexago.domains.Investment;
import com.pdi.hexago.domains.customers.Customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Account {

    private UUID id;
    private String accountNumber;
    private Customer cliente;
    private BigDecimal limit;
    private BigDecimal balance;
    private List<Investment> investments;

     public Account(String accountNumber,
                    // Customer cliente,
                    BigDecimal limit,
                    BigDecimal balance){
        this.accountNumber = accountNumber;
        this.limit = limit;
        this.balance = balance;
    }

     public void withDraw(BigDecimal valor){
        balance = balance.subtract(valor);
    }

    void deposit(BigDecimal valor){
        balance = balance.add(valor);
    }

    void transfer(Account target, BigDecimal valor){
        withDraw(valor);
        target.deposit(valor);
    }

    void invest(BigDecimal valor){

    }
}
