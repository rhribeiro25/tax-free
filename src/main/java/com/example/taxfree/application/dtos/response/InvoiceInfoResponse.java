package com.example.taxfree.application.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record InvoiceInfoResponse(
    String invoiceNumber,
    String hashTermination,
    String documentKey,
    String type,
    LocalDate date,
    BigDecimal grossTotal,
    BigDecimal refundableAmount,
    List<InvoiceLineInfoResponse> lines,
    List<CalculatedTaxInfoResponse> calculatedTaxes
) {}