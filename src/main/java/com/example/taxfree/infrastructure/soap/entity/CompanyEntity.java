package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {

    private Integer taxRegistrationNumber;
    private String businessName;
    private String email;
}
