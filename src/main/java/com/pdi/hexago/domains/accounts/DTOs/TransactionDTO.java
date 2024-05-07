package com.pdi.hexago.domains.accounts.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(String accountNumber, BigDecimal amount) {
}
