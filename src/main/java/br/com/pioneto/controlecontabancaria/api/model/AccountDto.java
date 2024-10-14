package br.com.pioneto.controlecontabancaria.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    private Integer id;
    private String number;
    private String creationDate;
    private String accountType;
}
