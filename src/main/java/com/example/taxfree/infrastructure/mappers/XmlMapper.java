package com.example.taxfree.infrastructure.mappers;

import com.example.taxfree.infrastructure.soap.entity.*;
import com.example.taxfree.infrastructure.utils.ParseUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class XmlMapper {

    // ----------------------------------------
    // BUILD REQUESTS
    // ----------------------------------------

    public static String buildSubmitRequestXml(ClaimEntity claim) {

        var buyer = claim.getCommunication().getBuyer();
        var invoice = claim.getCommunication().getInvoices().get(0);

        return """
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
            <soap:Body>
                <SubmitTaxFree>
                    <buyerName>%s</buyerName>
                    <buyerTaxNumber>%s</buyerTaxNumber>
                    <buyerCountry>%s</buyerCountry>
                    <invoiceNumber>%s</invoiceNumber>
                    <invoiceDate>%s</invoiceDate>
                    <grossTotal>%s</grossTotal>
                </SubmitTaxFree>
            </soap:Body>
        </soap:Envelope>
        """.formatted(
                buyer.getFullName(),
                buyer.getTaxNumber(),
                buyer.getResidenceCountryCode(),
                invoice.getIdentifiers().getInvoiceNumber(),
                invoice.getDate(),
                invoice.getGrossTotal()
        );
    }

    public static String buildSearchRequestXml(String taxFreeCode) {
        return """
            <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                <soap:Body>
                    <SearchTaxFree>
                        <taxFreeCode>%s</taxFreeCode>
                    </SearchTaxFree>
                </soap:Body>
            </soap:Envelope>
        """.formatted(taxFreeCode);
    }

    // ----------------------------------------
    // PARSE RESPONSES
    // ----------------------------------------

    public static ClaimEntity parseSubmitedResponse(String xml) {

        String documentKey = extract(xml, "<documentKey>", "</documentKey>");
        String code = extract(xml, "<returnCode>", "</returnCode>");
        String message = extract(xml, "<returnMessage>", "</returnMessage>");

        return ClaimEntity.builder()
                .documentKey(documentKey)
                .status("SUCCESS")
                .returnInfo(ReturnInfoEntity.builder()
                        .code(code)
                        .message(message)
                        .build())
                .build();
    }


    public static ClaimEntity parseSearchedResponse(String xml) {

        // -----------------------------
        // Return Info
        // -----------------------------
        ReturnInfoEntity returnInfo = ReturnInfoEntity.builder()
                .code(extract(xml, "<returnCode>", "</returnCode>"))
                .message(extract(xml, "<returnMessage>", "</returnMessage>"))
                .build();


        // -----------------------------
        // Buyer
        // -----------------------------
        BuyerEntity buyer = BuyerEntity.builder()
                .fullName(extract(xml, "<name>", "</name>"))
                .residenceCountryCode(extract(xml, "<residenceCountryCode>", "</residenceCountryCode>"))
                .taxNumber(ParseUtils.parseInt(extract(xml, "<taxRegistrationNumber>", "</taxRegistrationNumber>")))
                .birthdate(ParseUtils.parseDate(extract(xml, "<birthDate>", "</birthDate>")))
                .identityDocument(
                        IdentityDocumentEntity.builder()
                                .type(extract(xml, "<identityDocType>", "</identityDocType>"))
                                .number(extract(xml, "<identityDocNumber>", "</identityDocNumber>"))
                                .countryCode(extract(xml, "<identityDocCountry>", "</identityDocCountry>"))
                                .build()
                )
                .build();


        // -----------------------------
        // Seller (Company)
        // -----------------------------
        CompanyEntity seller = CompanyEntity.builder()
                .taxRegistrationNumber(ParseUtils.parseInt(extract(xml, "<sellerTaxNumber>", "</sellerTaxNumber>")))
                .businessName(extract(xml, "<sellerBusinessName>", "</sellerBusinessName>"))
                .email(extract(xml, "<sellerEmail>", "</sellerEmail>"))
                .build();


        // -----------------------------
        // Refund block
        // -----------------------------
        RefundEntity refund = RefundEntity.builder()
                .guaranteeTotal(ParseUtils.parseDecimal(extract(xml, "<guaranteeTotal>", "</guaranteeTotal>")))
                .calculatedTaxTotal(ParseUtils.parseDecimal(extract(xml, "<calculatedTaxTotal>", "</calculatedTaxTotal>")))
                .refundableAmount(ParseUtils.parseDecimal(extract(xml, "<refundableAmount>", "</refundableAmount>")))
                .build();


        // -----------------------------
        // Invoices
        // -----------------------------
        List<InvoiceEntity> invoices = new ArrayList<>();

        List<String> invoiceBlocks = extractList(xml, "<invoice>", "</invoice>");

        invoiceBlocks.forEach(block -> {

            InvoiceIdentifiersEntity identifiers = InvoiceIdentifiersEntity.builder()
                    .invoiceNumber(extract(block, "<invoiceNo>", "</invoiceNo>"))
                    .hashTermination(extract(block, "<hashTermination>", "</hashTermination>"))
                    .documentKey(extract(block, "<documentKey>", "</documentKey>"))
                    .build();

            List<CalculatedTaxEntity> calculatedTaxes = new ArrayList<>();
            List<String> taxBlocks = extractList(block, "<calculatedTax>", "</calculatedTax>");

            taxBlocks.forEach(taxBlock -> {
                calculatedTaxes.add(
                        CalculatedTaxEntity.builder()
                                .taxAmount(ParseUtils.parseDecimal(extract(taxBlock, "<taxAmount>", "</taxAmount>")))
                                .taxPercentage(ParseUtils.parseDecimal(extract(taxBlock, "<taxPercentage>", "</taxPercentage>")))
                                .build()
                );
            });

            List<InvoiceLineEntity> lines = new ArrayList<>();
            List<String> lineBlocks = extractList(block, "<invoiceLine>", "</invoiceLine>");

            lineBlocks.forEach(lb -> {
                lines.add(
                        InvoiceLineEntity.builder()
                                .itemCategory(extract(lb, "<productClass>", "</productClass>"))
                                .description(extract(lb, "<productDescription>", "</productDescription>"))
                                .quantity(ParseUtils.parseDecimal(extract(lb, "<quantity>", "</quantity>")))
                                .taxBaseTotal(ParseUtils.parseDecimal(extract(lb, "<taxBaseTotal>", "</taxBaseTotal>")))
                                .build()
                );
            });

            invoices.add(
                    InvoiceEntity.builder()
                            .identifiers(identifiers)
                            .invoiceType(extract(block, "<invoiceType>", "</invoiceType>"))
                            .date(ParseUtils.parseDate(extract(block, "<invoiceDate>", "</invoiceDate>")))
                            .grossTotal(ParseUtils.parseDecimal(extract(block, "<grossTotal>", "</grossTotal>")))
                            .refundableAmount(ParseUtils.parseDecimal(extract(block, "<refundableAmount>", "</refundableAmount>")))
                            .calculatedTaxes(calculatedTaxes)
                            .lines(lines)
                            .build()
            );
        });


        // -----------------------------
        // TaxFreeComm
        // -----------------------------
        TaxFreeCommEntity comm = TaxFreeCommEntity.builder()
                .identifier(extract(xml, "<taxFreeCommCode>", "</taxFreeCommCode>"))
                .buyer(buyer)
                .seller(seller)
                .refund(refund)
                .invoices(invoices)
                .build();


        // -----------------------------
        // ClaimEntity final
        // -----------------------------
        return ClaimEntity.builder()
                .id(comm.getIdentifier())
                .documentKey(extract(xml, "<documentKey>", "</documentKey>"))
                .returnInfo(returnInfo)
                .communication(comm)
                .status("FOUND")
                .build();
    }



    // ----------------------------------------
    // HELPERS
    // ----------------------------------------

    private static String extract(String xml, String start, String end) {
        try {
            int i = xml.indexOf(start);
            if (i == -1) return null;
            return xml.substring(i + start.length(), xml.indexOf(end, i));
        } catch (Exception e) {
            return null;
        }
    }

    private static List<String> extractList(String xml, String startTag, String endTag) {
        List<String> list = new ArrayList<>();
        int index = 0;

        while (true) {
            int start = xml.indexOf(startTag, index);
            if (start == -1) break;

            int end = xml.indexOf(endTag, start);
            if (end == -1) break;

            list.add(xml.substring(start, end + endTag.length()));
            index = end + endTag.length();
        }

        return list;
    }

}
