package com.pdi.hexago.web;

import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.domains.accounts.exceptions.AccountAlreadyExistsException;
import com.pdi.hexago.domains.accounts.service.IOperationsService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
}
