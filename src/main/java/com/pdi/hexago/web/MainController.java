package com.pdi.hexago.web;

import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.domains.accounts.DTOs.AmountDTO;
import com.pdi.hexago.domains.accounts.exceptions.AccountAlreadyExistsException;
import com.pdi.hexago.domains.accounts.exceptions.AccountNotFoundException;
import com.pdi.hexago.domains.accounts.service.IOperationsService;
import com.pdi.hexago.domains.accounts.DTOs.TransactionDTO;
import com.pdi.hexago.domains.customers.DTOs.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MainController {

    private final IOperationsService IOperationsService;

    MainController(IOperationsService IOperationsService) {
        this.IOperationsService = IOperationsService;
    }


    @GetMapping(value = "/")
    public String hello(){
        IOperationsService.withDraw("", BigDecimal.TEN);
        return "Hello World";
    }

    @PostMapping(value = "/account")
    public ResponseEntity<AccountDTO> account(@RequestBody AccountDTO account) throws AccountAlreadyExistsException {
        AccountDTO accountDTO = IOperationsService.createAccount(account);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping(value="/customer/{customerdoc}/accounts")
    public ResponseEntity<List<AccountDTO>> customerAccounts(@PathVariable("customerDoc") String customerDoc){
        List<AccountDTO> accounts = IOperationsService.listAccounts(customerDoc);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(value = "/account/amount/{accountNumber}")
    public ResponseEntity<AmountDTO> getAccount(@PathVariable("accountNumber") String accountNumber) {
        AmountDTO amount = IOperationsService.getAmount(accountNumber);
        return ResponseEntity.ok(amount);
    }

    @PostMapping(value = "/account/deposit")
    public String deposit(@RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {
        IOperationsService.deposit(transactionDTO);
        return "";
    }

}
