package br.com.zup.asgard.asgardbank.service;

import br.com.zup.asgard.asgardbank.dto.AccountDto;
import br.com.zup.asgard.asgardbank.exception.AccountAlreadyExistsException;
import br.com.zup.asgard.asgardbank.exception.AccountNotFoundException;
import br.com.zup.asgard.asgardbank.exception.CustomerNotFoundException;
import br.com.zup.asgard.asgardbank.helper.AccountHelper;
import br.com.zup.asgard.asgardbank.helper.CustomerHelper;
import br.com.zup.asgard.asgardbank.model.Account;
import br.com.zup.asgard.asgardbank.model.Customer;
import br.com.zup.asgard.asgardbank.repository.AccountRepository;
import br.com.zup.asgard.asgardbank.repository.CustomerRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void shouldFindAccountByNumber() throws AccountNotFoundException {

        final Long originAccountNumber = 123456L;
        final Account account = AccountHelper.buildAccount();

        when(accountRepository.findByOriginAccountNumber(originAccountNumber))
                .thenReturn(Optional.of(account));

        final AccountDto accountDto = accountService.searchAccountByNumber(originAccountNumber);

        Assertions.assertNotNull(accountDto);

        Assertions.assertEquals(originAccountNumber, accountDto.getOriginAccountNumber());

    }

    @Test
    public void shouldCreateAccount()
            throws CustomerNotFoundException, AccountAlreadyExistsException {

        final String validCpf = "33546492838";
        final Customer customer = CustomerHelper.buildCustomer();
        final Account account = AccountHelper.buildAccount();
        final AccountDto accountDto = AccountHelper.buildAccount().toAccountDto();

        when(customerRepository.findByCpf(validCpf))
                .thenReturn(Optional.of(customer));

        when(accountRepository.save(any())).thenReturn(account);

        AccountDto createdAccountDto = accountService.createAccount(validCpf, accountDto);

        Assertions.assertEquals(accountDto.getCustomer().getCpf(),
                createdAccountDto.getCustomer().getCpf()
        );

        verify(accountRepository, Mockito.times(1))
                .save(any());

    }

    @Test
    public void shouldDeleteAccount() throws AccountNotFoundException {

        final Long id = 1L;
        final Account account = AccountHelper.buildAccount();

        when(accountRepository.findById(id))
                .thenReturn(Optional.of(account));

        doNothing().when(accountRepository)
                .delete(account);

        AccountDto deletedAccountDto = accountService.deleteAccount(id);

        verify(accountRepository).delete(account);

        verify(accountRepository, Mockito.times(1))
                .delete(account);

        Assertions.assertFalse(deletedAccountDto.getId().toString().isEmpty());
        
    }

    @Test
    public void shouldObtainAllAccounts() throws AccountNotFoundException {

        final List<Account> accounts = AccountHelper.buildAccountList();

        when(accountRepository.findAll())
                .thenReturn(accounts);

        List<AccountDto> accountDtoList = accountService.obtainAllAccounts();

        Assertions.assertNotNull(accountDtoList);

        MatcherAssert.assertThat(accountDtoList, hasSize(equalTo(2)));

    }

    @Test
    @DisplayName(value = "It should throw exception if the customer is not found by cpf")
    public void shouldThrowExceptionIfCustomerIsNotFound() {

        final String cpf = "11111111111";
        final AccountDto accountDto = AccountHelper.buildAccount().toAccountDto();

        when(customerRepository.findByCpf(cpf))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                CustomerNotFoundException.class,
                () -> accountService.createAccount(cpf, accountDto));

    }

    @Test
    @DisplayName(value = "It should throw an exception if the account is not found by account number")
    public void shouldThrowsExceptionIfAccountIsNotFound() {

        final Long originAccountNumber = 0L;

        when(accountRepository.findByOriginAccountNumber(originAccountNumber))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                AccountNotFoundException.class,
                () -> accountService.searchAccountByNumber(originAccountNumber));

    }

    @Test
    public void shouldThrowExceptionIfAccountIsNotFoundById() {

        final Long id = 0L;

        when(accountRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                AccountNotFoundException.class,
                () -> accountService.deleteAccount(id));

    }

}


















