package com.example.taxfree.application.dtos.response;

import java.math.BigDecimal;

public record RefundInfoResponse(
    BigDecimal totalGuarantee,
    BigDecimal totalCalculatedTax,
    BigDecimal refundableAmount
) {}