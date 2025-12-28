package com.example.taxfree.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class TaxFreeComm {
    private final String identifier;
    private final String hashTermination;
    private final Buyer buyer;
    private final Company seller;
    private final Refund refund;
    private final List<Invoice> invoices;

    @Builder
    public TaxFreeComm(String identifier, String hashTermination, Buyer buyer, Company seller, Refund refund, List<Invoice> invoices) {
        if (identifier == null || identifier.isBlank()) throw new IllegalArgumentException("Identifier is required");
        if (buyer == null) throw new IllegalArgumentException("Buyer is required");
        if (seller == null) throw new IllegalArgumentException("Seller is required");
        if (invoices == null || invoices.isEmpty()) throw new IllegalArgumentException("At least one invoice is required");
        this.identifier = identifier;
        this.hashTermination = hashTermination;
        this.buyer = buyer;
        this.seller = seller;
        this.refund = refund;
        this.invoices = Collections.unmodifiableList(invoices);
    }
}