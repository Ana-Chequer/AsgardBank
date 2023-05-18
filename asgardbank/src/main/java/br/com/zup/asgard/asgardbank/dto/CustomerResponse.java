package br.com.zup.asgard.asgardbank.dto;

import br.com.zup.asgard.asgardbank.model.Customer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;

    private String cpf;

    private LocalDate birthDate;

    private String address;

    private String telephone;

    private String email;

    public CustomerResponse(Long id, String name, String cpf, LocalDate birthDate, String address, String telephone, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public CustomerResponse convertToCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getCpf(), customer.getBirthDate(), customer.getAddress(), customer.getTelephone(), customer.getEmail());
    }

}
