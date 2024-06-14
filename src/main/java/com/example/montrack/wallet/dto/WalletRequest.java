package com.example.montrack.wallet.dto;

import lombok.Data;

@Data
public class WalletRequest {
    private String name;
    private double amount;
}