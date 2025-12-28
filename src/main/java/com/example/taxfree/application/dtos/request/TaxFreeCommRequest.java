package com.example.taxfree.application.dtos.request;

import java.util.List;

public record TaxFreeCommRequest (
    String identifier,
    BuyerRequest buyer,
    CompanyRequest seller,
    RefundRequest refund,
    List<InvoiceRequest> invoices
){}