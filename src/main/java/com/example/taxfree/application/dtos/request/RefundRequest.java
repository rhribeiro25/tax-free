package com.example.taxfree.application.dtos.request;

import java.math.BigDecimal;

public record RefundRequest(
    BigDecimal guaranteeTotal,
    BigDecimal calculatedTaxTotal,
    BigDecimal refundableAmount
){}
