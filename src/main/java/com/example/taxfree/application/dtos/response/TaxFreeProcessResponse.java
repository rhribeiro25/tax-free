package com.example.taxfree.application.dtos.response;

import java.time.LocalDate;
import java.util.List;

public record TaxFreeProcessResponse(
    String processId,
    String status,
    String documentKey,
    LocalDate creationDate,
    LocalDate processedDate,
    RefundInfoResponse refund,
    List<InvoiceInfoResponse> invoices
) {}