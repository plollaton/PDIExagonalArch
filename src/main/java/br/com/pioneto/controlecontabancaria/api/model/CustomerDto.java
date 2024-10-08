package br.com.pioneto.controlecontabancaria.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private List<AccountDto> accountDtos = new ArrayList<>();
}
