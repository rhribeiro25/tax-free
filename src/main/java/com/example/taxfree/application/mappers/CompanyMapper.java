package com.example.taxfree.application.mappers;

import com.example.taxfree.application.dtos.request.CompanyRequest;
import com.example.taxfree.domain.model.Company;
import com.example.taxfree.infrastructure.soap.entity.CompanyEntity;

public class CompanyMapper {

    public static CompanyEntity toEntity(Company company) {
        if (company == null) return null;
        return CompanyEntity.builder()
                .taxRegistrationNumber(company.getTaxRegistrationNumber())
                .businessName(company.getBusinessName())
                .email(company.getEmail())
                .build();
    }

    public static Company toDomain(CompanyEntity entity) {
        if (entity == null) return null;
        return Company.builder()
                .taxRegistrationNumber(entity.getTaxRegistrationNumber())
                .businessName(entity.getBusinessName())
                .email(entity.getEmail())
                .build();
    }

    public static Company toDomain(CompanyRequest dto) {
        if (dto == null) return null;
        return Company.builder()
                .taxRegistrationNumber(dto.taxRegistrationNumber())
                .businessName(dto.businessName())
                .email(dto.email())
                .build();
    }

    public static CompanyRequest toDto(Company company) {
        if (company == null) return null;
        return new CompanyRequest(
                company.getTaxRegistrationNumber(),
                company.getBusinessName(),
                company.getEmail()
        );
    }

}
