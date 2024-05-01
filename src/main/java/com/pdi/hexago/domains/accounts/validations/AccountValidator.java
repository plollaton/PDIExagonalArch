package com.pdi.hexago.domains.accounts.validations;

import br.com.fluentvalidator.AbstractValidator;
import com.pdi.hexago.domains.accounts.Account;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class AccountValidator extends AbstractValidator<Account> {
    @Override
    public void rules() {
        setPropertyOnContext("Account");

        ruleFor(Account::getAccountNumber)
            .must(not(nullValue()))
                .withMessage("Account number not valid")
                .withFieldName("accountNumber");

        ruleFor(Account::getCustomer)
            .must(not(nullValue()))
                .withMessage("Customer not valid")
                .withFieldName("customer");

    }
}
