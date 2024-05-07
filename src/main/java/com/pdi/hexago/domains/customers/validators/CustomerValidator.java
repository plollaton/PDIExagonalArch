package com.pdi.hexago.domains.customers.validators;

import br.com.fluentvalidator.AbstractValidator;
import com.pdi.hexago.domains.customers.Customer;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class CustomerValidator extends AbstractValidator<Customer> {
    @Override
    public void rules() {
        setPropertyOnContext("Customer");

        ruleFor(Customer::getDocumentNumber)
            .must(not(nullValue()))
            .withMessage("Document number must have a value.")
            .withFieldName("Document Number");
    }
}
