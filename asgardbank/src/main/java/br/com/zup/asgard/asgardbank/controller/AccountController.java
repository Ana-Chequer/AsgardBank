package br.com.zup.asgard.asgardbank.controller;

import br.com.zup.asgard.asgardbank.dto.AccountDto;
import br.com.zup.asgard.asgardbank.dto.AccountResponse;
import br.com.zup.asgard.asgardbank.exception.AccountAlreadyExistsException;
import br.com.zup.asgard.asgardbank.exception.AccountNotFoundException;
import br.com.zup.asgard.asgardbank.exception.CustomerNotFoundException;
import br.com.zup.asgard.asgardbank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{originAccountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse searchAccountByNumber(@PathVariable @Valid Long originAccountNumber)
            throws AccountNotFoundException {

        AccountDto accountDto = accountService.searchAccountByNumber(originAccountNumber);

        final AccountResponse accountResponse = new AccountResponse();

            return accountResponse.fromAccountDto(accountDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@RequestBody @Valid String cpf, AccountDto accountDto)
            throws CustomerNotFoundException, AccountAlreadyExistsException {

        AccountDto receivedAccountDto = accountService.createAccount(cpf, accountDto);

        final AccountResponse accountResponse = new AccountResponse();

        return accountResponse.fromAccountDto(receivedAccountDto);
    }

    public AccountResponse deleteAccount(@RequestBody @Valid Long id)
            throws AccountNotFoundException {

        AccountDto deletedAccount = accountService.deleteAccount(id);

        final AccountResponse accountResponse = new AccountResponse();

        return accountResponse.fromAccountDto(deletedAccount);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse obtainAllAccount() throws AccountNotFoundException {

        List<AccountDto> allAccounts = accountService.obtainAllAccounts();

        final AccountResponse accountResponse = new AccountResponse();

        return accountResponse.fromAccountDto((AccountDto) allAccounts);

    }
}





