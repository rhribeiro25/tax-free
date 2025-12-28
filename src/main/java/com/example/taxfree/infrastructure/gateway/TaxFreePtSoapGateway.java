package com.example.taxfree.infrastructure.gateway;

import com.example.taxfree.infrastructure.soap.entity.ClaimEntity;
import com.example.taxfree.infrastructure.mappers.XmlMapper;
import com.example.taxfree.infrastructure.soap.client.SoapClientStub;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaxFreePtSoapGateway implements TaxFreeGateway {

    private final SoapClientStub soapClient;

    @Override
    public ClaimEntity submitTaxFree(ClaimEntity claimEntity) {
        String requestXml = XmlMapper.buildSubmitRequestXml(claimEntity);

        String responseXml = soapClient.send(requestXml);

        return XmlMapper.parseSubmitedResponse(responseXml);
    }

    @Override
    public ClaimEntity searchTaxFree(String taxFreeCode) {
        String requestXml = XmlMapper.buildSearchRequestXml(taxFreeCode);

        String responseXml = soapClient.send(requestXml);

        return XmlMapper.parseSearchedResponse(responseXml);
    }
}
