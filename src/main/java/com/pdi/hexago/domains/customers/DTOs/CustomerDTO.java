package com.pdi.hexago.domains.customers.DTOs;

import java.util.UUID;

public record CustomerDTO(UUID id,
                          String name,
                          String email,
                          String documentNumber) {
}
