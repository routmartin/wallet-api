package com.martin.wallet_api.dto;

import com.martin.wallet_api.entity.Wallet;

import java.math.BigDecimal;

public record WalletResponse(
        Long id,
        String ownerName,
        BigDecimal balance
) {
    public static WalletResponse from(Wallet wallet) {
        return new WalletResponse(wallet.getId(), wallet.getOwnerName(), wallet.getBalance());
    }
}
