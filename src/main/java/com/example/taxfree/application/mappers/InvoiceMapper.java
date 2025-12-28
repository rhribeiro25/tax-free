package com.example.taxfree.application.mappers;

import com.example.taxfree.application.dtos.request.InvoiceRequest;
import com.example.taxfree.application.dtos.response.CalculatedTaxInfoResponse;
import com.example.taxfree.application.dtos.response.InvoiceInfoResponse;
import com.example.taxfree.application.dtos.response.InvoiceLineInfoResponse;
import com.example.taxfree.domain.model.Claim;
import com.example.taxfree.domain.model.Invoice;
import com.example.taxfree.domain.model.InvoiceLine;
import com.example.taxfree.domain.model.TaxFreeComm;
import com.example.taxfree.domain.model.enums.InvoiceType;
import com.example.taxfree.infrastructure.soap.entity.InvoiceEntity;
import com.example.taxfree.infrastructure.soap.entity.InvoiceLineEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceMapper {

    public static Invoice toDomain(InvoiceRequest dto) {
        if (dto == null) return null;

        return Invoice.builder()
            .lines(InvoiceLineMapper.toDomainList(dto.lines()))
            .date(dto.date())
            .type(dto.invoiceType())
            .build();
}

    public static InvoiceRequest toDto(Invoice invoice) {
        if (invoice == null) return null;

        return new InvoiceRequest(
            invoice.getDate(),
            invoice.getType(),
            InvoiceLineMapper.toDtoList(invoice.getLines())
        );
    }

    public static InvoiceEntity toEntity(Invoice invoice) {
        if (invoice == null) return null;
        List<InvoiceLineEntity> lines = invoice.getLines().stream()
                .map(InvoiceLineMapper::toEntity)
                .collect(Collectors.toList());

        return InvoiceEntity.builder()
                .date(invoice.getDate())
                .invoiceType(invoice.getType().name())
                .lines(lines)
                .build();
    }

    public static Invoice toDomain(InvoiceEntity entity) {
        if (entity == null) return null;
        List<InvoiceLine> lines = entity.getLines().stream()
                .map(InvoiceLineMapper::toDomain)
                .collect(Collectors.toList());

        return Invoice.builder()
                .date(entity.getDate())
                .type(InvoiceType.valueOf(entity.getInvoiceType()))
                .lines(lines)
                .build();
    }

    public static InvoiceInfoResponse toResponse(TaxFreeComm taxFreeComm, Invoice invoice) {
        if (invoice == null) return null;

        List<InvoiceLineInfoResponse> lineResponses = invoice.getLines().stream()
                .map(InvoiceLineMapper::toResponse)
                .collect(Collectors.toList());

        List<CalculatedTaxInfoResponse> taxResponses = invoice.getLines().stream()
                .map(InvoiceLine::getTax)
                .map(TaxMapper::toResponse)
                .collect(Collectors.toList());

        return new InvoiceInfoResponse(
                taxFreeComm.getIdentifier(),
                taxFreeComm.getHashTermination(),
                taxFreeComm.getBuyer().getDocument().getNumber(),
                invoice.getType() != null ? invoice.getType().name() : null,
                invoice.getDate(),
                invoice.totalGross().getValue(),
                invoice.totalNet().getValue(),
                lineResponses,
                taxResponses
        );
    }

    public static List<InvoiceInfoResponse> toResponse(TaxFreeComm taxFreeComm, List<Invoice> invoices) {
        if (invoices == null) return null;
        return invoices.stream()
                .map(invoice -> toResponse(taxFreeComm, invoice))
                .collect(Collectors.toList());
    }

    public static List<Invoice> toInvoiceList(List<InvoiceEntity> entities) {
        if (entities == null) return List.of();

        return entities.stream()
                .map(InvoiceMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static InvoiceInfoResponse toResponse(Claim claim) {
        if (claim == null) return null;

        TaxFreeComm comm = claim.getTaxFreeComm();

        return new InvoiceInfoResponse(
                comm.getIdentifier(),
                comm.getHashTermination(),
                claim.getDocumentKey(),
                comm.getInvoices().get(0).getType().name(),

                claim.getCreatedAt(),
                comm.getRefund().getGuaranteeTotal(),
                comm.getRefund().getRefundableAmount(),
                InvoiceLineMapper.toResponse(comm.getInvoices().get(0).getLines()),
                List.of(
                        new CalculatedTaxInfoResponse(comm.getRefund().getCalculatedTaxTotal(),
                                comm.getRefund().getCalculatedTaxTotal())
                ));
    }

}
