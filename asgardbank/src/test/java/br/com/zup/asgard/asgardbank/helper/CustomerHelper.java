package br.com.zup.asgard.asgardbank.helper;

import br.com.zup.asgard.asgardbank.model.Customer;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class CustomerHelper {

    public static Customer buildCustomer() {
        return new Customer(
                1L,
                "Shuri",
                "33546492838",
                LocalDate.of(2019, 10, 16),
                "Rua A, 170",
                "11984268747",
                "shuri@gmail.com"
        );
    }

    public static Customer buildUpdatedCustomer() {
        return new Customer(
                1L,
                "Shuri Chequer",
                "33546492838",
                LocalDate.of(2019, 10, 16),
                "Rua A, 170 - apto. 502",
                "11983511003",
                "shuri.chequer@gmail.com"
        );
    }

}
