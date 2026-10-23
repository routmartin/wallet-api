package com.martin.wallet_api.reopository;

import com.martin.wallet_api.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByOwnerNameContainingIgnoreCase(String ownerName);
}
