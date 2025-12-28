package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEntity {

    private InvoiceIdentifiersEntity identifiers;

    private String invoiceType;

    private LocalDate date;
    private BigDecimal grossTotal;
    private BigDecimal refundableAmount;

    private List<CalculatedTaxEntity> calculatedTaxes;
    private List<InvoiceLineEntity> lines;
}
