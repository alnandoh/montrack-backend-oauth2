package com.example.montrack.wallet;

import java.util.List;
import java.util.Optional;

public interface WalletService {
    Wallet createWallet(Long userId, Wallet wallet);
    List<Wallet> getAllWallets(Long userId);
    Optional<Wallet> getWalletById(Long userId, Long walletId);
    Wallet updateWallet(Long userId, Long walletId, Wallet wallet);
    void deleteWallet(Long userId, Long walletId);
    Wallet setMainWallet(Long userId, Long walletId);
}
