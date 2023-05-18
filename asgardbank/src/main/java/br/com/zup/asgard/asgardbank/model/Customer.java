package br.com.zup.asgard.asgardbank.model;


import br.com.zup.asgard.asgardbank.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false, unique = true, length = 11)
    private String telephone;

    @Column(unique = true, length = 50)
    private String email;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt = LocalDate.now();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Account account;

    public Customer(Long id,
                    String name,
                    String cpf,
                    LocalDate birthDate,
                    String address,
                    String telephone,
                    String email)
    {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public CustomerDto toDto() {
        return new CustomerDto(
                this.id,
                this.name,
                this.cpf,
                this.birthDate,
                this.address,
                this.telephone,
                this.email
        );
    }

}
