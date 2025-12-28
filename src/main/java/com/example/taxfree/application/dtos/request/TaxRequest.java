package com.example.taxfree.application.dtos.request;

import java.math.BigDecimal;

public record TaxRequest (
    String code,
    MoneyRequest base,
    BigDecimal rate
){}