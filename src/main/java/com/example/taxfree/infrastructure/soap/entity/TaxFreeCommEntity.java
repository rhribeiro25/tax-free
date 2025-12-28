package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxFreeCommEntity {

    private String identifier;
    private BuyerEntity buyer;
    private CompanyEntity seller;
    private RefundEntity refund;
    private List<InvoiceEntity> invoices;
}
