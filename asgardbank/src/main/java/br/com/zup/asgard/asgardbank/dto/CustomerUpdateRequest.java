package br.com.zup.asgard.asgardbank.dto;

import br.com.zup.asgard.asgardbank.model.Customer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CustomerUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank(message = "Only DDD code and numbers")
    private String telephone;

    @NotBlank
    @Email
    private String email;

    public CustomerUpdateRequest(String name, String address, String telephone, String email) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public Customer convertPartialCustomerModel() {
        Customer customerPartialUpdated = new Customer();
        customerPartialUpdated.setName(name);
        customerPartialUpdated.setAddress(address);
        customerPartialUpdated.setTelephone(telephone);
        customerPartialUpdated.setEmail(email);

        return customerPartialUpdated;
    }


}
