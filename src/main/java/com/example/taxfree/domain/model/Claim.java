package com.example.taxfree.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Claim {
    private final String id;
    private final ReturnInfo returnInfo;
    private final TaxFreeComm taxFreeComm;

    private final LocalDate createdAt;
    private final LocalDate processedAt;

    private final String status;
    private final String documentKey;
}
