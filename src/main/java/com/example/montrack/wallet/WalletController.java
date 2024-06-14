package com.example.montrack.wallet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestParam Long userId, @RequestBody Wallet wallet) {
        return ResponseEntity.ok(walletService.createWallet(userId, wallet));
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets(@RequestParam Long userId) {
        return ResponseEntity.ok(walletService.getAllWallets(userId));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<Optional<Wallet>> getWalletById(@RequestParam Long userId, @PathVariable Long walletId) {
        return ResponseEntity.ok(walletService.getWalletById(userId, walletId));
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<Wallet> updateWallet(@RequestParam Long userId, @PathVariable Long walletId, @RequestBody Wallet wallet) {
        return ResponseEntity.ok(walletService.updateWallet(userId, walletId, wallet));
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> deleteWallet(@RequestParam Long userId, @PathVariable Long walletId) {
        walletService.deleteWallet(userId, walletId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{walletId}/set-main")
    public ResponseEntity<Wallet> setMainWallet(@RequestParam Long userId, @PathVariable Long walletId) {
        return ResponseEntity.ok(walletService.setMainWallet(userId, walletId));
    }
}