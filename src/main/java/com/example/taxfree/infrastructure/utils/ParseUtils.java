package com.example.taxfree.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
public final class ParseUtils {

    private ParseUtils() {}

    public static BigDecimal parseDecimal(String v) {
        if (v == null || v.isBlank()) {
            return null;
        }

        try {
            return new BigDecimal(v);
        } catch (Exception e) {
            log.warn("parseDecimal() inválido: '{}'", v);
            return null;
        }
    }

    public static Integer parseInt(String v) {
        if (v == null || v.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            log.warn("parseInt() inválido: '{}'", v);
            return null;
        }
    }

    public static LocalDate parseDate(String v) {
        if (v == null || v.isBlank()) {
            return null;
        }

        try {
            return LocalDate.parse(v);
        } catch (Exception e) {
            log.warn("parseDate() inválido: '{}'", v);
            return null;
        }
    }
}
