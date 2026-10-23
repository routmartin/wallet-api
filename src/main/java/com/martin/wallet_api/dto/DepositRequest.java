package com.martin.wallet_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositRequest(
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Deposit amount must be greater than zero")
        @Digits(
                integer = 17,
                fraction = 2,
                message = "Amount must hae at most two decimal places"
        )
        BigDecimal amount
) {
}
