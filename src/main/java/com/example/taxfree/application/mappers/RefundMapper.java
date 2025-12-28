package com.example.taxfree.application.mappers;

import com.example.taxfree.application.dtos.request.RefundRequest;
import com.example.taxfree.application.dtos.response.RefundInfoResponse;
import com.example.taxfree.domain.model.Refund;
import com.example.taxfree.infrastructure.soap.entity.RefundEntity;

public class RefundMapper {

    public static RefundEntity toEntity(Refund refund) {
        if (refund == null) return null;
        return RefundEntity.builder()
                .guaranteeTotal(refund.getGuaranteeTotal())
                .calculatedTaxTotal(refund.getCalculatedTaxTotal())
                .refundableAmount(refund.getRefundableAmount())
                .build();
    }

    public static Refund toDomain(RefundEntity entity) {
        if (entity == null) return null;
        return Refund.builder()
                .guaranteeTotal(entity.getGuaranteeTotal())
                .calculatedTaxTotal(entity.getCalculatedTaxTotal())
                .refundableAmount(entity.getRefundableAmount())
                .build();
    }

    public static Refund toDomain(RefundRequest dto) {
        if (dto == null) return null;
        return Refund.builder()
                .guaranteeTotal(dto.guaranteeTotal())
                .calculatedTaxTotal(dto.calculatedTaxTotal())
                .refundableAmount(dto.refundableAmount())
                .build();
    }

    public static RefundRequest toDto(Refund refund) {
        if (refund == null) return null;
        return new RefundRequest(
                refund.getGuaranteeTotal(),
                refund.getCalculatedTaxTotal(),
                refund.getRefundableAmount()
        );
    }

    public static RefundInfoResponse toResponse(Refund refund) {
        if (refund == null) return null;
        return new RefundInfoResponse(
                refund.getGuaranteeTotal(),
                refund.getCalculatedTaxTotal(),
                refund.getRefundableAmount()
        );
    }
}
