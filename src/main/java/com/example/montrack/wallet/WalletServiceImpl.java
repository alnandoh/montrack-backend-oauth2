package com.example.montrack.wallet;

import com.example.montrack.user.entity.User;
import com.example.montrack.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public Wallet createWallet(Long userId, Wallet wallet) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        wallet.setUser(user);
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    public List<Wallet> getAllWallets(Long userId) {
        return walletRepository.findByUserId(userId);
    }

    public Optional<Wallet> getWalletById(Long userId, Long walletId) {
        return walletRepository.findById(walletId).filter(wallet -> wallet.getUser().getId().equals(userId));
    }

    public Wallet updateWallet(Long userId, Long walletId, Wallet walletDetails) {
        Wallet wallet = getWalletById(userId, walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        wallet.setName(walletDetails.getName());
        wallet.setAmount(walletDetails.getAmount());
        wallet.setUpdatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    public void deleteWallet(Long userId, Long walletId) {
        Wallet wallet = getWalletById(userId, walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        walletRepository.delete(wallet);
    }

    public Wallet setMainWallet(Long userId, Long walletId) {
        List<Wallet> wallets = walletRepository.findByUserId(userId);
        wallets.forEach(wallet -> {
            if (wallet.getId().equals(walletId)) {
                wallet.setMain(true);
            } else {
                wallet.setMain(false);
            }
            walletRepository.save(wallet);
        });
        return getWalletById(userId, walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
    }
}
