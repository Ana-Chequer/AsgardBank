package br.com.zup.asgard.asgardbank.service;

import br.com.zup.asgard.asgardbank.dto.AccountDto;
import br.com.zup.asgard.asgardbank.exception.AccountAlreadyExistsException;
import br.com.zup.asgard.asgardbank.exception.AccountNotFoundException;
import br.com.zup.asgard.asgardbank.exception.CustomerNotFoundException;
import br.com.zup.asgard.asgardbank.model.Account;
import br.com.zup.asgard.asgardbank.model.Customer;
import br.com.zup.asgard.asgardbank.repository.AccountRepository;
import br.com.zup.asgard.asgardbank.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public AccountDto searchAccountByNumber(Long originAccountNumber) throws AccountNotFoundException {

        Optional<Account> accountByNumber = Optional.of(
                accountRepository.findByOriginAccountNumber(originAccountNumber)
                        .orElseThrow(() -> new AccountNotFoundException()
                        )
        );

        final Account account = accountByNumber.get();

        return new AccountDto(
                account.getId(),
                account.getOriginAccountNumber(),
                account.getAvailableAmount(),
                account.getCustomer()
        );

    }

     public AccountDto createAccount(String cpf, AccountDto accountDto)
            throws CustomerNotFoundException, AccountAlreadyExistsException {

        Optional<Customer> foundCustomer = Optional.of(
                customerRepository.findByCpf(cpf).orElseThrow(() -> new CustomerNotFoundException()
                )
        );

        final Customer customer = foundCustomer.get();
        final Account customerAccount = customer.getAccount();


        if (customerAccount != null) {
            throw new AccountAlreadyExistsException();

        }

        final Long accountNumber = createAccountNumber();
        final Account createdAccount = accountDto.toModel();

        createdAccount.setOriginAccountNumber(accountNumber);

        final Account account = accountRepository.save(createdAccount);

        return accountDto.fromModel(account);
    }


    public AccountDto deleteAccount(Long id) throws AccountNotFoundException {

        Optional<Account> foundAccount = Optional.of(
                accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException()
                )
        );

        final Account account = foundAccount.get();
        
        accountRepository.delete(account);

           return account.toAccountDto();

    }

    public List<AccountDto> obtainAllAccounts() throws AccountNotFoundException {

        final List<AccountDto> accountDtoList = new ArrayList<>();
        final List<Account> allAccounts =  accountRepository.findAll();

        final AccountDto accountDto = new AccountDto();
        for (Account accounts: allAccounts) {
            accountDtoList.add(accountDto.fromModel(accounts));
        }

        if(accountDtoList.isEmpty()) {
            throw new AccountNotFoundException();
        }

        return accountDtoList;
    }

    private Long createAccountNumber() {

        final SecureRandom randomNumber = new SecureRandom();
        int upperbound = 1000000;
        int number = randomNumber.nextInt(upperbound);
        final Long accountNumber = Long.valueOf(number);

            return accountNumber;

    }

}
