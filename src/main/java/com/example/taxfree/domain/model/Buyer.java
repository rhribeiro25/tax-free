package com.example.taxfree.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Buyer {
    private final String fullName;
    private final String nationality;
    private final String fiscalNumber;
    private final String country;
    private final LocalDate birthdate;
    private final Document document;

    @Builder
    public Buyer(String fullName, String nationality, String fiscalNumber, String country, LocalDate birthdate, Document document) {
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Full name is required");
        if (birthdate == null)
            throw new IllegalArgumentException("Birthdate is required");
        this.fullName = fullName;
        this.nationality = nationality;
        this.fiscalNumber = fiscalNumber;
        this.country = country;
        this.birthdate = birthdate;
        this.document = document;
    }

    public boolean isAdult() {
        return birthdate.plusYears(18).isBefore(LocalDate.now());
    }
}