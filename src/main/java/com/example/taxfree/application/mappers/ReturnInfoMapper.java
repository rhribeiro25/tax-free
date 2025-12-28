package com.example.taxfree.application.mappers;

import com.example.taxfree.domain.model.ReturnInfo;
import com.example.taxfree.infrastructure.soap.entity.ReturnInfoEntity;

public class ReturnInfoMapper {
    public static ReturnInfo toDomain(ReturnInfoEntity entity) {
        if (entity == null) return null;

        return ReturnInfo.builder()
                .code(entity.getCode())
                .message(entity.getMessage())
                .build();
    }
}
