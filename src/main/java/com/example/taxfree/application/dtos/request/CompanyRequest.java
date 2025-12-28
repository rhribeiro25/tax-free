package com.example.taxfree.application.dtos.request;

public record CompanyRequest (
    Integer taxRegistrationNumber,
    String businessName,
    String email
){}
