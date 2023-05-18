package br.com.zup.asgard.asgardbank.helper;

import br.com.zup.asgard.asgardbank.model.Account;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AccountHelper {

    public static Account buildAccount() {
        return new Account(
                1L,
                123456L,
                BigDecimal.valueOf(10000),
                CustomerHelper.buildCustomer()
        );
    }

    public static Account buildOtherAccount() {
        return new Account(
                2L,
                654321L,
                BigDecimal.valueOf(11000),
                CustomerHelper.buildCustomer()
        );
    }

    public static Account buildUpdatedAccount() {
        return new Account(
                1L,
                654321L,
                BigDecimal.valueOf(10000),
                CustomerHelper.buildCustomer()
        );
    }

    public static List <Account> buildAccountList() {
        List <Account> accounts = new ArrayList<>();
        accounts.add(buildAccount());
        accounts.add(buildOtherAccount());

        return accounts;

    }

}
