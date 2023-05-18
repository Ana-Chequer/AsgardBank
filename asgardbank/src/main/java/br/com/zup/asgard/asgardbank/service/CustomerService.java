package br.com.zup.asgard.asgardbank.service;

import br.com.zup.asgard.asgardbank.dto.CustomerRequest;
import br.com.zup.asgard.asgardbank.dto.CustomerResponse;
import br.com.zup.asgard.asgardbank.dto.CustomerUpdateRequest;
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

    public CustomerResponse searchCustomerByCpf(String cpf) {

        CustomerResponse  customerResponse = new CustomerResponse();
       Optional<Customer> customer = customerRepository.findByCpf(cpf);

        if(customer.isPresent()) {
            return customerResponse.convertToCustomerResponse(customer.get());
        }
        return null;
    }

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {

        CustomerResponse  customerResponse = new CustomerResponse();
        Customer createdCustomer = customerRequest.convertToCustomerModel();
        Customer customer = customerRepository.save(createdCustomer);

           return customerResponse.convertToCustomerResponse(customer);
    }

    public CustomerResponse deleteCustomer(Long id) {

        CustomerResponse  customerResponse = new CustomerResponse();
        Optional<Customer> customer = customerRepository.findById(id).


        if (customer.isPresent()) {
            Customer recoveredCustomer = customer.get();
            customerRepository.delete(recoveredCustomer);
            return customerResponse.convertToCustomerResponse(recoveredCustomer);
        }


        return new CustomerResponse();
    }


    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest customerUpdateRequest) {

        CustomerResponse  customerResponse = new CustomerResponse();
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()) {
            Customer recoveredCustomer = customer.get();
            BeanUtils.copyProperties(customerUpdateRequest, recoveredCustomer, "id", "cpf", "birthDate");
            Customer updatedCustomer = customerRepository.save(recoveredCustomer);
            return customerResponse.convertToCustomerResponse(updatedCustomer);
        }

        return new CustomerResponse();
    }
}
