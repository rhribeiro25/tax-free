package com.example.taxfree.application.dtos.response;

import java.math.BigDecimal;

public record InvoiceLineInfoResponse(
    String category,
    String description,
    BigDecimal quantity,
    BigDecimal netAmount
) {}