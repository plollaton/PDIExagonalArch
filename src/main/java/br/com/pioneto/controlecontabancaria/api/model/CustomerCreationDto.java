package br.com.pioneto.controlecontabancaria.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerCreationDto {

    private String nome;
    private String cpf;
    private String email;
}
