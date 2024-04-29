package com.pdi.hexago.web;

import com.pdi.hexago.domains.accounts.DTOs.AccountDTO;
import com.pdi.hexago.domains.accounts.service.IOperationsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String account(@RequestBody AccountDTO account){
        IOperationsService.createAccount(account);
        return "";
    }
}
