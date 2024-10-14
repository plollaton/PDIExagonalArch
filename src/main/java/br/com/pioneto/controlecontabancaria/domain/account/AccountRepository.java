package br.com.pioneto.controlecontabancaria.domain.account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
}
