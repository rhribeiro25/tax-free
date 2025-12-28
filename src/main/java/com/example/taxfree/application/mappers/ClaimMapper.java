package com.example.taxfree.application.mappers;

import com.example.taxfree.domain.model.Claim;
import com.example.taxfree.infrastructure.soap.entity.ClaimEntity;

public class ClaimMapper {

    public static Claim toDomain(ClaimEntity entity) {
        if (entity == null) return null;

        return Claim.builder()
                .id(entity.getId())
                .returnInfo(ReturnInfoMapper.toDomain(entity.getReturnInfo()))
                .taxFreeComm(TaxMapper.toDomain(entity.getCommunication()))
                .createdAt(entity.getCreatedAt())
                .processedAt(entity.getProcessedAt())
                .status(entity.getStatus())
                .documentKey(entity.getDocumentKey())
                .build();
    }

}
