package com.example.taxfree.domain.model;

import com.example.taxfree.domain.model.enums.DocumentType;
import lombok.Builder;
import lombok.Getter;


@Getter
public class Document {
    private final DocumentType type;
    private final String number;
    private final String country;

    @Builder
    public Document(DocumentType type, String number, String country) {
        if (type == null || number == null || number.isBlank())
            throw new IllegalArgumentException("Document type and number are required");
        this.type = type;
        this.number = number;
        this.country = country;
    }
}