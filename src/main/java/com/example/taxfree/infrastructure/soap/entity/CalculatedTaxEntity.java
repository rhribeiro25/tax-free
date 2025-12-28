package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculatedTaxEntity {

    private BigDecimal taxAmount;
    private BigDecimal taxPercentage;
}
