package com.example.taxfree.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Company {
    private final Integer taxRegistrationNumber;
    private final String businessName;
    private final String email;

    @Builder
    public Company(Integer taxRegistrationNumber, String businessName, String email) {
        if (taxRegistrationNumber == null)
            throw new IllegalArgumentException("Tax Registration Number is required");
        if (businessName == null || businessName.isBlank())
            throw new IllegalArgumentException("Business Name is required");
        this.taxRegistrationNumber = taxRegistrationNumber;
        this.businessName = businessName;
        this.email = email;
    }
}
