package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceIdentifiersEntity {

    private String invoiceNumber;
    private String hashTermination;
    private String documentKey;
}
