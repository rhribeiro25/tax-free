package com.example.taxfree.application.dtos.request;

import com.example.taxfree.domain.model.enums.InvoiceType;

import java.time.LocalDate;
import java.util.List;

public record InvoiceRequest (
    LocalDate date,
    InvoiceType invoiceType,
    List<InvoiceLineRequest> lines
){}