package br.com.zup.asgard.asgardbank.service;

import br.com.zup.asgard.asgardbank.dto.CustomerDto;
import br.com.zup.asgard.asgardbank.exception.CustomerNotDeletedException;
import br.com.zup.asgard.asgardbank.exception.CustomerNotFoundException;
import br.com.zup.asgard.asgardbank.model.Account;
import br.com.zup.asgard.asgardbank.model.Customer;
import br.com.zup.asgard.asgardbank.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto searchCustomerByCpf(String cpf) throws CustomerNotFoundException {

        Optional<Customer> foundCustomer = Optional.of(
                customerRepository.findByCpf(cpf)
                        .orElseThrow(() -> new CustomerNotFoundException()
                )
        );

        final Customer customer = foundCustomer.get();
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getBirthDate(),
                customer.getAddress(),
                customer.getTelephone(),
                customer.getEmail()
        );
    }

    public CustomerDto searchCustomerById(Long id) throws CustomerNotFoundException {

        Optional<Customer> foundCustomer = Optional.of(
                customerRepository.findById(id)
                        .orElseThrow(() -> new CustomerNotFoundException()
                        )
        );

        final Customer customer = foundCustomer.get();
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getCpf(),
                customer.getBirthDate(),
                customer.getAddress(),
                customer.getTelephone(),
                customer.getEmail()
        );
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {

        final Customer createdCustomer = customerDto.toModel();
        final Customer customer = customerRepository.save(createdCustomer);

            return customerDto.fromModel(customer);
        }

    public CustomerDto deleteCustomer(Long id)
            throws CustomerNotFoundException, CustomerNotDeletedException {

        Optional<Customer> foundCustomer = Optional.of(
                customerRepository.findById(id)
                        .orElseThrow(() -> new CustomerNotFoundException()
                )
        );

        final Customer customer = foundCustomer.get();
        final Account customerAccount = customer.getAccount();

        if(customerAccount != null) {
            throw new CustomerNotDeletedException();

        } else {
            customerRepository.delete(customer);
        }

        return customer.toDto();
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) throws CustomerNotFoundException {

        Optional<Customer> recoveredCustomer = Optional.of(
                customerRepository.findById(id)
                        .orElseThrow(() -> new CustomerNotFoundException()
                        )
        );

        final Customer updatedCustomer = recoveredCustomer.get();
        BeanUtils.copyProperties(customerDto, updatedCustomer, "id", "cpf", "birthDate");
        final Customer customer = customerRepository.save(updatedCustomer);

            return customerDto.fromModel(customer);
    }


}
