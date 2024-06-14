package com.example.montrack.budget;

import com.example.montrack.wallet.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name="budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_id_gen")
    @SequenceGenerator(name = "budget_id_gen", sequenceName = "budget_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @NotNull
    @NotBlank
    private String emoji;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private Integer amount;

    @NotNull
    @NotBlank
    private Integer amount_left;

    @NotNull
    @NotBlank
    private String description;

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
