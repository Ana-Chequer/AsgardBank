package br.com.zup.asgard.asgardbank.dto;

import br.com.zup.asgard.asgardbank.model.Account;
import br.com.zup.asgard.asgardbank.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    private Long originAccountNumber;

    @PositiveOrZero(message = "Available amount must be positive or zero")
    private BigDecimal availableAmount;

    private Customer customer;

    public Account toModel() {
        return new Account(
                id,
                originAccountNumber,
                availableAmount,
                customer
        );
    }

    public AccountDto fromModel(Account account) {
        return new AccountDto(
                account.getId(),
                account.getOriginAccountNumber(),
                account.getAvailableAmount(),
                account.getCustomer()
        );
    }

}
