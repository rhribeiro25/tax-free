package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyerEntity {

    private String fullName;
    private String residenceCountryCode;
    private Integer taxNumber;
    private LocalDate birthdate;
    private IdentityDocumentEntity identityDocument;
}
