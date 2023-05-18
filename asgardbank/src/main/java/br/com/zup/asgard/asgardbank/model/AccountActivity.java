package br.com.zup.asgard.asgardbank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "account_activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @JoinColumn(name = "account_activity_type_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountActivityType activity;

    @JoinColumn(name = "account_activity_statement_type_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountActivityStatementType statement;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @JoinColumn(name = "current_account_id", nullable = false)
    @ManyToOne
    private Account account;
}

