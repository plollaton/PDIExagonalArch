package com.pdi.hexago.domains.accounts;

import com.pdi.hexago.domains.Investment;
import com.pdi.hexago.domains.customers.Customer;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public class Account {

    private UUID id;
    private String accountNumber;
    private Customer customer;
    private BigDecimal limit;
    private BigDecimal balance;
    private List<Investment> investments;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
