package com.example.taxfree.domain.model.enums;

public enum DocumentType {
    PASSPORT("Passport"),
    ID_CARD("Identity Card"),
    DRIVER_LICENSE("Driver License");

    private final String description;

    DocumentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
