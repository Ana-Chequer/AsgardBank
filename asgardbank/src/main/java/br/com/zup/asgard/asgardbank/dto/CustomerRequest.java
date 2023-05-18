package br.com.zup.asgard.asgardbank.dto;

import br.com.zup.asgard.asgardbank.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank
    private String name;

    @NotBlank(message = "Only numbers")
    @CPF
    private String cpf;

    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotBlank
    private String address;

    @NotBlank(message = "Only DDD code and numbers")
    private String telephone;

   @NotBlank
   @Email
    private String email;

    public CustomerRequest(String name, String cpf, LocalDate birthDate, String address, String telephone, String email) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public Customer convertToCustomerModel() {
        return new Customer(name, cpf, birthDate, address, telephone, email);
    }

}
