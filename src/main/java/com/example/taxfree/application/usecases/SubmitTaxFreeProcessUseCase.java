package com.example.taxfree.application.usecases;

import com.example.taxfree.application.dtos.request.TaxFreeCommRequest;
import com.example.taxfree.application.dtos.response.InvoiceInfoResponse;
import com.example.taxfree.application.mappers.ClaimMapper;
import com.example.taxfree.application.mappers.InvoiceMapper;
import com.example.taxfree.domain.model.Claim;
import com.example.taxfree.domain.services.TaxFreeDomainService;
import com.example.taxfree.infrastructure.gateway.TaxFreeGateway;
import com.example.taxfree.infrastructure.soap.entity.ClaimEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmitTaxFreeProcessUseCase implements UseCaseTemplate<TaxFreeCommRequest, InvoiceInfoResponse> {

    private final TaxFreeDomainService domainService;
    private final TaxFreeGateway taxFreeGateway;

    @Override
    public void validate(TaxFreeCommRequest taxFreeCommRequest) {
        //Could implement some validation using design pattern facade
    }

    @Override
    public InvoiceInfoResponse execute(TaxFreeCommRequest request) {
        Claim claim = ClaimMapper.toDomain(taxFreeGateway.submitTaxFree(
        ClaimEntity.builder()
        .id("djsajdiojasoidjioasjdiojasio")
                .build()));

        this.validate(request);

        //Apply some business logic using Service Layer

        return InvoiceMapper.toResponse(claim);
    }

}
