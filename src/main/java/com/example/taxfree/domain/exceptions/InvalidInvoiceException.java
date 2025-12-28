package com.example.taxfree.domain.exceptions;

public class InvalidInvoiceException extends RuntimeException {

    public InvalidInvoiceException(String message) {
        super(message);
    }

    public InvalidInvoiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
