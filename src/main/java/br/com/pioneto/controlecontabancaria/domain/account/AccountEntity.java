package br.com.pioneto.controlecontabancaria.domain.account;

import br.com.pioneto.controlecontabancaria.api.model.CustomerDto;
import br.com.pioneto.controlecontabancaria.domain.customer.CustomerEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String number;
    private String creationDate;
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private CustomerEntity customer;
}
