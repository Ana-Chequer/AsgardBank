package br.com.zup.asgard.asgardbank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CustomerResponse {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotBlank
    private String address;

    @NotBlank
    private String telephone;

    @NotBlank
    @Email
    private String email;

    public CustomerResponse(Long id,
                            String name,
                            String cpf,
                            LocalDate birthDate,
                            String address,
                            String telephone,
                            String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public CustomerResponse fromCustomerDto(CustomerDto customerDto) {
        return new CustomerResponse(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getCpf(),
                customerDto.getBirthDate(),
                customerDto.getAddress(),
                customerDto.getTelephone(),
                customerDto.getEmail()
        );
    }
}
