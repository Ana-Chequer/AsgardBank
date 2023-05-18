package br.com.zup.asgard.asgardbank.controller;

import br.com.zup.asgard.asgardbank.dto.CustomerDto;
import br.com.zup.asgard.asgardbank.exception.CustomerNotFoundException;
import br.com.zup.asgard.asgardbank.helper.CustomerHelper;
import br.com.zup.asgard.asgardbank.repository.CustomerRepository;
import br.com.zup.asgard.asgardbank.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMock
    private CustomerController customerController;

   @Test
   public void shouldFindCustomerById() {

       final String cpf = "33546492838";
       final CustomerDto customerDto = CustomerHelper.buildCustomer().toDto();
       final CustomerResponse customerResponse = CustomerHelper.

        when(customerService.searchCustomerByCpf(cpf))
                .thenReturn(customerDto);











   }


}
