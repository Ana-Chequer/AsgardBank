package br.com.zup.asgard.asgardbank.model;

import br.com.zup.asgard.asgardbank.dto.AccountDto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "current_account")
//@Data
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_account_number", nullable = false)
    private Long originAccountNumber;

    @Column(name = "available_amount")
    private BigDecimal availableAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    /*@OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<AccountActivity> accountActivities;*/

    public Account(Long id,
                   Long originAccountNumber,
                   BigDecimal availableAmount,
                   Customer customer) {

        this.id = id;
        this.originAccountNumber = originAccountNumber;
        this.availableAmount = availableAmount;
        this.customer = customer;
    }

    public AccountDto toAccountDto() {
        return new AccountDto(
                this.id,
                this.originAccountNumber,
                this.availableAmount,
                this.customer
        );
    }

}
