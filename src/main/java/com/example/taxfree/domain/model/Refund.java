package com.example.taxfree.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Refund {
    private final BigDecimal guaranteeTotal;
    private final BigDecimal calculatedTaxTotal;
    private final BigDecimal refundableAmount;

    @Builder
    public Refund(BigDecimal guaranteeTotal, BigDecimal calculatedTaxTotal, BigDecimal refundableAmount) {
        this.guaranteeTotal = guaranteeTotal;
        this.calculatedTaxTotal = calculatedTaxTotal;
        this.refundableAmount = refundableAmount;
    }
}