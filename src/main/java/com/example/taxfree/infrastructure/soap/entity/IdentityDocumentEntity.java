package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityDocumentEntity {

    private String type;
    private String number;
    private String countryCode;
}
