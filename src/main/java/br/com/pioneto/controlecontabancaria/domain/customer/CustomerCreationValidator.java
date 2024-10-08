package br.com.pioneto.controlecontabancaria.domain.customer;

import br.com.fluentvalidator.AbstractValidator;
import br.com.pioneto.controlecontabancaria.api.model.CustomerCreationDto;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class CustomerCreationValidator extends AbstractValidator<CustomerCreationDto> {

    @Override
    public void rules() {
        setPropertyOnContext("Customer");

        ruleFor(CustomerCreationDto::getNome)
                .must(not(nullValue()))
                .withMessage("Customer must have a name.")
                .withFieldName("name");

        ruleFor(CustomerCreationDto::getEmail)
                .must(not(nullValue()))
                .withMessage("Customer must have an email address.")
                .withFieldName("email");

        ruleFor(CustomerCreationDto::getCpf)
                .must(not(nullValue()))
                .withMessage("Customer must have a cpf.")
                .withFieldName("cpf");
    }
}
