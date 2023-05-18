package br.com.zup.asgard.asgardbank.dto;

import br.com.zup.asgard.asgardbank.model.Account;
import br.com.zup.asgard.asgardbank.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponse {

    private Long id;

    private Long accountNumberOrigin;

   private BigDecimal availableAmount;

    public AccountResponse(Long id,
                           Long accountNumberOrigin,
                           BigDecimal availableAmount
    ) {
        this.id = id;
        this.accountNumberOrigin = accountNumberOrigin;
        this.availableAmount = availableAmount;
    }

    public AccountResponse fromAccountDto(AccountDto accountDto) {
        return new AccountResponse(
                accountDto.getId(),
                accountDto.getOriginAccountNumber(),
                accountDto.getAvailableAmount());
    }
}
