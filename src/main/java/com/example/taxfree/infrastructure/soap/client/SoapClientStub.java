package com.example.taxfree.infrastructure.soap.client;

public class SoapClientStub {
    public String send(String xmlRequest) {
        if (xmlRequest.contains("<SubmitTaxFree>")) {
            return """
                    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                        <soap:Body>
                            <SubmitTaxFreeResponse>
                                <status>SUCCESS</status>
                                <documentKey>DOC-123456789</documentKey>
                                <message>Processed Successfully</message>
                            </SubmitTaxFreeResponse>
                        </soap:Body>
                    </soap:Envelope>
                """;
        }

        if (xmlRequest.contains("<SearchTaxFree>")) {
            return """
                    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                        <soap:Body>
                            <SearchTaxFreeResponse>
                                <status>FOUND</status>
                                <documentKey>DOC-123456789</documentKey>
                                <refundValue>59.90</refundValue>
                                <message>Record Found</message>
                            </SearchTaxFreeResponse>
                        </soap:Body>
                    </soap:Envelope>
                """;
        }

        return """
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap:Body>
                        <Error>Unknown Request</Error>
                    </soap:Body>
                </soap:Envelope>
                """;
    }
}
