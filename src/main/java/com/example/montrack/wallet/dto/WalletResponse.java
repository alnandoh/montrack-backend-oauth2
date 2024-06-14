package com.example.montrack.wallet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WalletResponse {
    private Long id;
    private String name;
    private double amount;
    private boolean isMain;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
