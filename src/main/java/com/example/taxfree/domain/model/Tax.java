package com.example.taxfree.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Tax {
    private final String code;
    private final Money base;
    private final BigDecimal rate;

    @Builder
    public Tax(String code, Money base, BigDecimal rate) {
        this.code = code;
        this.base = base;
        this.rate = rate;
    }

    public Money calculate() {
        return base.multiply(rate);
    }
}