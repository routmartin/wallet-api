package com.martin.wallet_api.controller;

import com.martin.wallet_api.dto.DepositRequest;
import com.martin.wallet_api.dto.WalletRequest;
import com.martin.wallet_api.dto.WalletResponse;
import com.martin.wallet_api.entity.Wallet;
import com.martin.wallet_api.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public WalletResponse createWallet(@Valid @RequestBody WalletRequest request) {
        Wallet wallet = walletService.createWallet(request.ownerName());
        return WalletResponse.from(wallet);
    }

    @GetMapping("/{id:\\d+}")
    public WalletResponse getById(@PathVariable Long id) {
        return WalletResponse.from(walletService.getWalletById(id));
    }

    @GetMapping()
    public Page<WalletResponse> getWallets(Pageable pageable) {
        return walletService.getWallets(pageable).map(WalletResponse::from);
    }

    @GetMapping("/search")
    public List<WalletResponse> searchWallet(@RequestParam String ownerName) {

        return walletService.searchWallets(ownerName)
                .stream()
                .map(WalletResponse::from)
                .toList();
    }

    @PostMapping("/{id}/deposit")
    public WalletResponse deposit(
            @PathVariable Long id,
            @Valid @RequestBody DepositRequest request
    ) {
        Wallet wallet = walletService.deposit(id, request.amount());
        return WalletResponse.from(wallet);
    }
}
