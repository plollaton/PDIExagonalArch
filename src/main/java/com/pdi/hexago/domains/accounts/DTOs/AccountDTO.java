package com.pdi.hexago.domains.accounts.DTOs;

import com.pdi.hexago.domains.customers.DTOs.CustomerDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountDTO(UUID id,
                         String accountNumber,
                         CustomerDTO customer,
                         BigDecimal amount) {
}
