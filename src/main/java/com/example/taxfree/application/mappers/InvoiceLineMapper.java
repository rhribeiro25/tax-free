package com.example.taxfree.application.mappers;

import com.example.taxfree.application.dtos.request.InvoiceLineRequest;
import com.example.taxfree.application.dtos.response.InvoiceLineInfoResponse;
import com.example.taxfree.domain.model.InvoiceLine;
import com.example.taxfree.domain.model.Money;
import com.example.taxfree.domain.model.Tax;
import com.example.taxfree.domain.model.enums.InvoiceCategoryType;
import com.example.taxfree.infrastructure.soap.entity.InvoiceLineEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceLineMapper {

    public static InvoiceLine toDomain(InvoiceLineRequest dto) {
        if (dto == null) return null;
        Money netValue = MoneyMapper.toDomain(dto.netValue());
        Tax tax = TaxMapper.toDomain(dto.tax());

        return InvoiceLine.builder()
                .category(dto.category())
                .description(dto.description())
                .quantity(dto.quantity())
                .netValue(netValue)
                .tax(tax)
                .build();
    }

    public static InvoiceLineRequest toDto(InvoiceLine line) {
        if (line == null) return null;
        return new InvoiceLineRequest(
                line.getCategory(),
                line.getDescription(),
                line.getQuantity(),
                MoneyMapper.toDto(line.getNetValue()),
                TaxMapper.toDto(line.getTax())
        );
    }

    public static List<InvoiceLine> toDomainList(List<InvoiceLineRequest> requests) {
        if (requests == null) return List.of();
        return requests.stream()
                .map(InvoiceLineMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<InvoiceLineRequest> toDtoList(List<InvoiceLine> lines) {
        if (lines == null) return List.of();
        return lines.stream()
                .map(InvoiceLineMapper::toDto)
                .collect(Collectors.toList());
    }

    public static InvoiceLineEntity toEntity(InvoiceLine line) {
        if (line == null) return null;
        return InvoiceLineEntity.builder()
                .itemCategory(line.getCategory().name())
                .description(line.getDescription())
                .quantity(BigDecimal.valueOf(line.getQuantity()))
                .taxBaseTotal(line.getNetValue().getValue())
                .build();
    }

    public static InvoiceLine toDomain(InvoiceLineEntity entity) {
        if (entity == null) return null;
        Money net = Money.builder()
                .value(entity.getTaxBaseTotal())
                .currency(com.example.taxfree.domain.model.enums.CurrencyType.EUR)
                .build();

        Tax tax = Tax.builder()
                .base(net)
                .rate(BigDecimal.ZERO)
                .build();

        return InvoiceLine.builder()
                .category(InvoiceCategoryType.valueOf(entity.getItemCategory()))
                .description(entity.getDescription())
                .quantity(entity.getQuantity().intValue())
                .netValue(net)
                .tax(tax)
                .build();
    }

    public static InvoiceLineInfoResponse toResponse(InvoiceLine line) {
        if (line == null) return null;
        return new InvoiceLineInfoResponse(
                line.getCategory().name(),
                line.getDescription(),
                BigDecimal.valueOf(line.getQuantity()),
                line.totalNet().getValue()
        );
    }

    public static List<InvoiceLineInfoResponse> toResponse(List<InvoiceLine> lines) {
        if (lines == null) return null;
        return lines.stream()
                .map(InvoiceLineMapper::toResponse)
                .collect(Collectors.toList());
    }


}
