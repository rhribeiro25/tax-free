package com.example.taxfree.application.usecases;

import com.example.taxfree.application.dtos.request.TaxFreeSearchRequest;
import com.example.taxfree.application.dtos.response.InvoiceInfoResponse;
import com.example.taxfree.application.mappers.ClaimMapper;
import com.example.taxfree.application.mappers.InvoiceMapper;
import com.example.taxfree.domain.model.Claim;
import com.example.taxfree.infrastructure.gateway.TaxFreeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchTaxFreeProcessUseCase implements UseCaseTemplate<TaxFreeSearchRequest, InvoiceInfoResponse> {

    private final TaxFreeGateway taxFreeGateway;

    @Override
    public void validate(TaxFreeSearchRequest taxFreeSearchRequest) {
        //Could implement some validation using design pattern facade
    }

    @Override
    public InvoiceInfoResponse execute(TaxFreeSearchRequest request) {
        if (request.taxFreeCode() == null || request.taxFreeCode().isBlank()) {
            throw new IllegalArgumentException("TaxFree code is required");
        }
        Claim claim = ClaimMapper.toDomain(taxFreeGateway.searchTaxFree(request.taxFreeCode()));

        this.validate(request);

        //Apply some business logic using Service Layer

        return InvoiceMapper.toResponse(claim);
    }

}
