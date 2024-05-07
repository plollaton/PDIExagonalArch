package com.pdi.hexago.domains.accounts.DTOs;

import java.math.BigDecimal;

public record AmountDTO(String accountNumber, BigDecimal amount) {
}
