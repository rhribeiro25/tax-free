package com.example.taxfree.application.dtos.response;

import java.math.BigDecimal;

public record CalculatedTaxInfoResponse(
    BigDecimal amount,
    BigDecimal percentage
) {}
