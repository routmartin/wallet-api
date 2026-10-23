package com.martin.wallet_api.service;

import com.martin.wallet_api.entity.Wallet;
import com.martin.wallet_api.exception.ResourceNotFoundException;
import com.martin.wallet_api.reopository.WalletRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository repo) {
        this.walletRepository = repo;
    }

    @Transactional
    public Wallet createWallet(String ownerName) {
        Wallet wallet = new Wallet(ownerName);
        return walletRepository.save(wallet);
    }

    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found:" + id));
    }


    public Page<Wallet> getWallets(Pageable pageable) {
        return walletRepository.findAll(pageable);
    }

    public List<Wallet> searchWallets(String ownerName) {
        return walletRepository.findByOwnerNameContainingIgnoreCase(ownerName);
    }

    @Transactional
    public Wallet deposit(Long walletId, BigDecimal amount) {

        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new ResourceNotFoundException(
                "Wallet not found: " + walletId
        ));

        wallet.setBalance(amount);
        return wallet;
    }


}
