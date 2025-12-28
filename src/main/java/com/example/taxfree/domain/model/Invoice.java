package com.example.taxfree.domain.model;

import com.example.taxfree.domain.model.enums.CurrencyType;
import com.example.taxfree.domain.model.enums.InvoiceType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter
public class Invoice {
    private final LocalDate date;
    private final InvoiceType type;
    private final List<InvoiceLine> lines;

    @Builder
    public Invoice(LocalDate date, InvoiceType type, List<InvoiceLine> lines) {
        if (date == null) throw new IllegalArgumentException("Invoice date required");
        if (lines == null || lines.isEmpty()) throw new IllegalArgumentException("Invoice must have lines");
        this.date = date;
        this.type = type;
        this.lines = Collections.unmodifiableList(lines);
    }

    public Money totalNet() {
        return lines.stream().map(InvoiceLine::totalNet).reduce(new Money(BigDecimal.ZERO, CurrencyType.EUR), Money::add);
    }

    public Money totalTax() {
        return lines.stream().map(InvoiceLine::totalTax).reduce(new Money(BigDecimal.ZERO, CurrencyType.EUR), Money::add);
    }

    public Money totalGross() {
        return lines.stream().map(InvoiceLine::totalGross).reduce(new Money(BigDecimal.ZERO, CurrencyType.EUR), Money::add);
    }
}
