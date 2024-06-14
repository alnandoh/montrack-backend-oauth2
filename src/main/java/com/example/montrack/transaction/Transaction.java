package com.example.montrack.transaction;

import com.example.montrack.budget.Budget;
import com.example.montrack.goal.Goal;
import com.example.montrack.wallet.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_gen")
    @SequenceGenerator(name = "transaction_id_gen", sequenceName = "transaction_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @NotNull
    @NotBlank
    private Integer amount;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @OneToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @NotNull
    @OneToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transaction_date;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = false)
    private LocalDateTime deletedAt;
}
