package com.example.taxfree.application.dtos.request;

import com.example.taxfree.domain.model.enums.CurrencyType;

import java.math.BigDecimal;

public record MoneyRequest(
    BigDecimal value,
    CurrencyType currency
){}