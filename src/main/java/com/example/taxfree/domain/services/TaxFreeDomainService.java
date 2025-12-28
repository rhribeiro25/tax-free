package com.example.taxfree.domain.services;

import com.example.taxfree.domain.model.Invoice;
import com.example.taxfree.domain.exceptions.InvalidInvoiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxFreeDomainService {

    public Invoice validateInvoice(Invoice invoice) {
        if (invoice.getLines() == null || invoice.getLines().isEmpty()) {
            throw new InvalidInvoiceException("Invoice must have at least one line");
        }
        invoice.getLines().forEach(line -> {
            if (line.getQuantity() <= 0 || !line.getNetValue().isPositive()) {
                throw new InvalidInvoiceException("Invalid line in invoice");
            }
        });
        return invoice;
    }
}
