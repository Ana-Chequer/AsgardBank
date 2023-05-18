package br.com.zup.asgard.asgardbank.repository;

import br.com.zup.asgard.asgardbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);

}
