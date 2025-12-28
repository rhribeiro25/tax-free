package com.example.taxfree.application.mappers;

import com.example.taxfree.application.dtos.request.DocumentRequest;
import com.example.taxfree.domain.model.Document;
import com.example.taxfree.domain.model.enums.DocumentType;
import com.example.taxfree.infrastructure.soap.entity.IdentityDocumentEntity;

public class DocumentMapper {

    public static IdentityDocumentEntity toEntity(Document document) {
        if (document == null) return null;
        return IdentityDocumentEntity.builder()
                .type(document.getType().name())
                .number(document.getNumber())
                .countryCode(document.getCountry())
                .build();
    }

    public static Document toDomain(IdentityDocumentEntity entity) {
        if (entity == null) return null;
        return Document.builder()
                .type(DocumentType.valueOf(entity.getType()))
                .number(entity.getNumber())
                .country(entity.getCountryCode())
                .build();
    }

    public static Document toDomain(DocumentRequest dto) {
        if (dto == null) return null;
        return Document.builder()
                .type(DocumentType.valueOf(dto.type()))
                .number(dto.number())
                .country(dto.country())
                .build();
    }

    public static DocumentRequest toDto(Document document) {
        if (document == null) return null;
        return new DocumentRequest(
                document.getType().name(),
                document.getNumber(),
                document.getCountry()
        );
    }
}
