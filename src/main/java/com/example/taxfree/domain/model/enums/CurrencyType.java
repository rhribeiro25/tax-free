package com.example.taxfree.domain.model.enums;

public enum CurrencyType {
    EUR("Euro"),
    USD("US Dollar"),
    GBP("British Pound");

    private final String description;

    CurrencyType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
