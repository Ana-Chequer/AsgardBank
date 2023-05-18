package br.com.zup.asgard.asgardbank.controller;

import br.com.zup.asgard.asgardbank.dto.CustomerRequest;
import br.com.zup.asgard.asgardbank.dto.CustomerResponse;
import br.com.zup.asgard.asgardbank.dto.CustomerUpdateRequest;
import br.com.zup.asgard.asgardbank.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> searchCustomer(@PathVariable @Valid String cpf) {

        CustomerResponse customerResponse = customerService.searchCustomerByCpf(cpf);
            if(customerResponse != null) {
            return ResponseEntity.ok().body("Customer has found");
        }
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerRequest customerRequest, UriComponentsBuilder uriComponentsBuilder) {

     CustomerResponse customerResponse = customerService.createCustomer(customerRequest);

     URI location = uriComponentsBuilder.path("/customerResponse/{id}").buildAndExpand(customerResponse.getId()).toUri();

        return ResponseEntity.created(location).body("Customer successfully created");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

        CustomerResponse customerResponse = customerService.deleteCustomer(id);
        if(customerResponse.getId() == id) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest customerUpdateRequest) {

        CustomerResponse customerResponse = customerService.updateCustomer(id, customerUpdateRequest);
        if(customerResponse.getId() > 0) {
            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.unprocessableEntity().body("Customer's data can not updated");

    }
}
