package br.com.pioneto.controlecontabancaria.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCreationDto {

    private Integer customerId;
    private String accountType;
}
