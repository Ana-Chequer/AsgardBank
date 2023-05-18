package br.com.zup.asgard.asgardbank.dto;

import br.com.zup.asgard.asgardbank.model.Account;
import br.com.zup.asgard.asgardbank.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Only numbers")
    @CPF(message = "Only numbers")
    private String cpf;

    @NotNull(message = "Birth date is mandatory")
    @Past(message = "Send a past date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Only DDD code and number")
    @Size(min = 11, max = 11)
    private String telephone;

    @NotEmpty(message = "Email valid is mandatory")
    @Email(message = "Email's format is incorrect")
    private String email;

    public Customer toModel() {
        return new Customer(
                id,
                name,
                cpf,
                birthDate,
                address,
                telephone,
                email
        );
    }

    public CustomerDto fromModel(Customer customer) {
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



}
