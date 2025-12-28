package com.example.taxfree.infrastructure.soap.entity;

import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceLineEntity {

    private String itemCategory;
    private String description;
    private BigDecimal quantity;
    private BigDecimal taxBaseTotal;
}
