package com.example.taxfree.application.dtos.request;

import java.time.LocalDate;

public record BuyerRequest(
    String fullName,
    String nationality,
    String fiscalNumber,
    String country,
    LocalDate birthdate,
    DocumentRequest document
) {}