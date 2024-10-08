package br.com.pioneto.controlecontabancaria.domain.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    List<CustomerEntity> findAll();
    Optional<CustomerEntity> findById(Integer id);
    CustomerEntity findByCpf(String cpf);
}
