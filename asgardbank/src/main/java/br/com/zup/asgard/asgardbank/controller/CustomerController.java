package br.com.zup.asgard.asgardbank.controller;

import br.com.zup.asgard.asgardbank.dto.CustomerDto;
import br.com.zup.asgard.asgardbank.dto.CustomerResponse;
import br.com.zup.asgard.asgardbank.exception.CustomerNotDeletedException;
import br.com.zup.asgard.asgardbank.exception.CustomerNotFoundException;
import br.com.zup.asgard.asgardbank.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse searchCustomerByCpf(@Valid @RequestParam String cpf)
            throws CustomerNotFoundException {

        final CustomerDto foundcustomerDto = customerService.searchCustomerByCpf(cpf);

        final CustomerResponse customerResponse = new CustomerResponse();

        return customerResponse.fromCustomerDto(foundcustomerDto);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse searchCustomerById(@PathVariable @Valid Long id)
            throws CustomerNotFoundException {

        final CustomerDto foundcustomerDto = customerService.searchCustomerById(id);

        final CustomerResponse customerResponse = new CustomerResponse();

        return customerResponse.fromCustomerDto(foundcustomerDto);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody @Valid CustomerDto customerDto) {

        final CustomerDto receivedCustomerDto = customerService.createCustomer(customerDto);

        final CustomerResponse customerResponse = new CustomerResponse();

        return customerResponse.fromCustomerDto(receivedCustomerDto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomerResponse deleteCustomer(@PathVariable Long id)
            throws CustomerNotFoundException, CustomerNotDeletedException {

        final CustomerDto deletedcustomerDto = customerService.deleteCustomer(id);

        final CustomerResponse customerResponse = new CustomerResponse();

        return customerResponse.fromCustomerDto(deletedcustomerDto);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDto customerDto)
            throws CustomerNotFoundException {

        final CustomerDto updatedCustomerDto = customerService.updateCustomer(id, customerDto);

        final CustomerResponse customerResponse = new CustomerResponse();

        return customerResponse.fromCustomerDto(updatedCustomerDto);

    }

}
