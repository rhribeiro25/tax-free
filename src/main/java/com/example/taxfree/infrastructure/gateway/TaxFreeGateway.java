package com.example.taxfree.infrastructure.gateway;

import com.example.taxfree.infrastructure.soap.entity.ClaimEntity;

public interface TaxFreeGateway {

    ClaimEntity submitTaxFree(ClaimEntity claimEntity);

    ClaimEntity searchTaxFree(String taxFreeCode);
}
