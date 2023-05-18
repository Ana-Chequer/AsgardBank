package br.com.zup.asgard.asgardbank.service;

import br.com.zup.asgard.asgardbank.exception.CustomerNotDeletedException;
import br.com.zup.asgard.asgardbank.exception.CustomerNotFoundException;
import br.com.zup.asgard.asgardbank.helper.CustomerHelper;
import br.com.zup.asgard.asgardbank.dto.CustomerDto;
import br.com.zup.asgard.asgardbank.model.Customer;
import br.com.zup.asgard.asgardbank.repository.CustomerRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    @Test
    public void shouldFindCustomerByCpf() throws CustomerNotFoundException {

        final String cpf = "33546492838";
        final Customer customer = CustomerHelper.buildCustomer();

        when(customerRepository.findByCpf(cpf))
                .thenReturn(Optional.of(customer));

        final CustomerDto customerDto = customerService.searchCustomerByCpf(cpf);

        Assertions.assertNotNull(customerDto);
    }

    @Test
    public void shouldCreateCustomer() {

        final CustomerDto customerDto = CustomerHelper.buildCustomer().toDto();
        final Customer customer = CustomerHelper.buildCustomer();

        when(customerRepository.save(customer))
                .thenReturn(customer);

        final CustomerDto savedCustomerDto = customerService.createCustomer(customerDto);

        Assertions.assertNotNull(savedCustomerDto);
    }

    @Test
    public void shouldDeleteCustomer() throws CustomerNotFoundException, CustomerNotDeletedException {

        final Long id = 1L;
        final Customer customer = CustomerHelper.buildCustomer();

        when(customerRepository.findById(id))
                .thenReturn(Optional.of(customer));

        doNothing().when(customerRepository)
                .delete(customer);

        CustomerDto deletedCustomer = customerService.deleteCustomer(id);

        verify(customerRepository, Mockito.times(1))
                .delete(customer);

        Assertions.assertEquals(customer.toDto(), deletedCustomer);

    }

    @Test
    public void shouldUpdateCustomer() throws CustomerNotFoundException {

        final Long id = 1L;
        final Customer customer = CustomerHelper.buildCustomer();
        final Customer updatedCustomer = CustomerHelper.buildUpdatedCustomer();
        final CustomerDto customerDto = updatedCustomer.toDto();

        when(customerRepository.findById(id))
                .thenReturn(Optional.of(customer));

        when(customerRepository.save(updatedCustomer))
                .thenReturn(updatedCustomer);

        CustomerDto updatedCustomerDto = customerService.updateCustomer(id, customerDto);

        assertThat(customerDto, equalTo(updatedCustomerDto));
    }

    @Test
    public void shouldThrowExceptionIfCustomerIsNotFoundByCpf() {

        final String cpf = "11111111111";

        when(customerRepository.findByCpf(cpf))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.searchCustomerByCpf(cpf)
        );
    }

    @Test
    public void shouldThrowExceptionsIfCustomerIsNotFoundById() {

        final Long id = 0L;

        when(customerRepository.findById(id))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.deleteCustomer(id)
        );
    }
}

