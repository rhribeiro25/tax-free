package com.example.taxfree.domain.model;

import com.example.taxfree.domain.model.enums.CurrencyType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Money {
    private final BigDecimal value;
    private final CurrencyType currency;

    @Builder
    public Money(BigDecimal value, CurrencyType currency) {
        if (value == null || currency == null)
            throw new IllegalArgumentException("Value and currency are required");
        this.value = value;
        this.currency = currency;
    }

    private void checkCurrency(Money other) {
        if (!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Cannot operate with different currencies");
    }

    public Money add(Money other) {
        checkCurrency(other);
        return new Money(this.value.add(other.value), this.currency);
    }

    public Money multiply(int factor) {
        return new Money(this.value.multiply(BigDecimal.valueOf(factor)), this.currency);
    }

    public Money multiply(BigDecimal factor) {
        return new Money(this.value.multiply(factor), this.currency);
    }

    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }
}