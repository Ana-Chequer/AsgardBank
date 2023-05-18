package br.com.zup.asgard.asgardbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "current_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number_origin")
    private Long accountNumberOrigin;

    @Column(name = "account_number_target", nullable = false)
    private Long accountNumberTarget;

    @Column(name = "available_amount")
    private BigDecimal availableAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt = LocalDate.now();

    @JoinColumn(name = "customer_id")
    @OneToOne
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<AccountActivity> accountActivities;

}
