package com.example.taxfree.application.dtos.request;

import com.example.taxfree.domain.model.enums.InvoiceCategoryType;

public record InvoiceLineRequest (
    InvoiceCategoryType category,
    String description,
    int quantity,
    MoneyRequest netValue,
    TaxRequest tax
){}