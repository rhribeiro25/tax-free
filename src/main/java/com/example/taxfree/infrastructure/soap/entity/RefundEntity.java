package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundEntity {

    private BigDecimal guaranteeTotal;
    private BigDecimal calculatedTaxTotal;
    private BigDecimal refundableAmount;
}
