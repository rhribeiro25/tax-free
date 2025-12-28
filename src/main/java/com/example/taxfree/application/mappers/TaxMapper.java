package com.example.taxfree.application.mappers;

import com.example.taxfree.application.dtos.request.TaxRequest;
import com.example.taxfree.application.dtos.response.CalculatedTaxInfoResponse;
import com.example.taxfree.domain.model.Money;
import com.example.taxfree.domain.model.Tax;
import com.example.taxfree.domain.model.TaxFreeComm;
import com.example.taxfree.infrastructure.soap.entity.CalculatedTaxEntity;
import com.example.taxfree.infrastructure.soap.entity.TaxFreeCommEntity;

public class TaxMapper {

    public static Tax toDomain(TaxRequest dto) {
        if (dto == null) return null;

        return Tax.builder()
                .rate(dto.rate())
                .base(MoneyMapper.toDomain(dto.base()))
                .build();
    }

    public static TaxRequest toDto(Tax tax) {
        if (tax == null) return null;

        return new TaxRequest(
                tax.getCode(),
                MoneyMapper.toDto(tax.getBase()),
                tax.getRate()
        );
    }

    public static CalculatedTaxEntity toEntity(Tax tax) {
        if (tax == null) return null;
        return CalculatedTaxEntity.builder()
                .taxAmount(tax.calculate().getValue())
                .taxPercentage(tax.getRate())
                .build();
    }

    public static Tax toDomain(CalculatedTaxEntity entity, Money base) {
        if (entity == null) return null;
        return Tax.builder()
                .base(base)
                .rate(entity.getTaxPercentage())
                .build();
    }

    public static CalculatedTaxInfoResponse toResponse(Tax tax) {
        if (tax == null) return null;
        return new CalculatedTaxInfoResponse(
                tax.calculate().getValue(),
                tax.getRate()
        );
    }

    public static TaxFreeComm toDomain(TaxFreeCommEntity entity) {
        if (entity == null) return null;

        return TaxFreeComm.builder()
                .identifier(entity.getIdentifier())
                .buyer(BuyerMapper.toDomain(entity.getBuyer()))
                .seller(CompanyMapper.toDomain(entity.getSeller()))
                .refund(RefundMapper.toDomain(entity.getRefund()))
                .invoices(InvoiceMapper.toInvoiceList(entity.getInvoices()))
                .hashTermination(resolveHashTermination(entity))
                .build();
    }

    private static String resolveHashTermination(TaxFreeCommEntity entity) {
        if (entity.getInvoices() == null || entity.getInvoices().isEmpty()) return null;
        if (entity.getInvoices().get(0).getIdentifiers() == null) return null;

        return entity.getInvoices().get(0).getIdentifiers().getHashTermination();
    }




}
