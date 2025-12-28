package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimEntity {

    private String id;
    private ReturnInfoEntity returnInfo;
    private TaxFreeCommEntity communication;

    private LocalDate createdAt;
    private LocalDate processedAt;

    private String status;
    private String documentKey;
}
