package com.example.taxfree.domain.model;

import com.example.taxfree.domain.model.enums.InvoiceCategoryType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InvoiceLine {
    private final InvoiceCategoryType category;
    private final String description;
    private final int quantity;
    private final Money netValue;
    private final Tax tax;

    @Builder
    public InvoiceLine(InvoiceCategoryType category, String description, int quantity, Money netValue, Tax tax) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (netValue == null || !netValue.isPositive()) throw new IllegalArgumentException("Net value must be positive");
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.netValue = netValue;
        this.tax = tax;
    }

    public Money totalNet() { return netValue.multiply(quantity); }
    public Money totalTax() { return tax.calculate(); }
    public Money totalGross() { return totalNet().add(totalTax()); }
}