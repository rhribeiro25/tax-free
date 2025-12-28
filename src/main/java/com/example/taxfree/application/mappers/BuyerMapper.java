package com.example.taxfree.application.mappers;

import com.example.taxfree.application.dtos.request.BuyerRequest;
import com.example.taxfree.domain.model.Buyer;
import com.example.taxfree.infrastructure.soap.entity.BuyerEntity;

public class BuyerMapper {

    public static Buyer toDomain(BuyerRequest dto) {
        if (dto == null) return null;
        return Buyer.builder()
                .fullName(dto.fullName())
                .nationality(dto.nationality())
                .fiscalNumber(dto.fiscalNumber())
                .country(dto.country())
                .birthdate(dto.birthdate())
                .document(DocumentMapper.toDomain(dto.document()))
                .build();
    }

    public static BuyerRequest toDto(Buyer buyer) {
        if (buyer == null) return null;

        return new BuyerRequest(
                buyer.getFullName(),
                buyer.getNationality(),
                buyer.getFiscalNumber(),
                buyer.getCountry(),
                buyer.getBirthdate(),
                DocumentMapper.toDto(buyer.getDocument())
        );
    }

    public static BuyerEntity toEntity(Buyer buyer) {
        if (buyer == null) return null;
        return BuyerEntity.builder()
                .fullName(buyer.getFullName())
                .birthdate(buyer.getBirthdate())
                .residenceCountryCode(buyer.getCountry())
                .taxNumber(buyer.getFiscalNumber() != null ? Integer.valueOf(buyer.getFiscalNumber()) : null)
                .identityDocument(DocumentMapper.toEntity(buyer.getDocument()))
                .build();
    }

    public static Buyer toDomain(BuyerEntity entity) {
        if (entity == null) return null;
        return Buyer.builder()
                .fullName(entity.getFullName())
                .birthdate(entity.getBirthdate())
                .country(entity.getResidenceCountryCode())
                .fiscalNumber(entity.getTaxNumber() != null ? String.valueOf(entity.getTaxNumber()) : null)
                .document(DocumentMapper.toDomain(entity.getIdentityDocument()))
                .build();
    }
}
