<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
  xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
  xmlns:dn="urn:oasis:names:specification:ubl:schema:xsd:DebitNote-2"
  exclude-result-prefixes="cbc cac xsl dn">
  <xsl:template match="/">
    <debitNote>
      <xsl:for-each select="dn:DebitNote">
        <documentId>
          <xsl:value-of select="cbc:ID"/>
        </documentId>
        <uniqueElectronicDocumentCode>
          <xsl:value-of select="cbc:UUID"/>
        </uniqueElectronicDocumentCode>
        <issueDate>
          <xsl:value-of select="cbc:IssueDate"/>
        </issueDate>

        <invoiceId>
          <xsl:value-of select="cac:BillingReference/cac:InvoiceDocumentReference/cbc:ID"/>
        </invoiceId>
        <uniqueElectronicInvoiceCode>
          <xsl:value-of select="cac:BillingReference/cac:InvoiceDocumentReference/cbc:UUID"/>
        </uniqueElectronicInvoiceCode>
        <invoiceIssueDate>
          <xsl:value-of select="cac:BillingReference/cac:InvoiceDocumentReference/cbc:IssueDate"/>
        </invoiceIssueDate>
        <reference>
          <xsl:value-of select="cac:DiscrepancyResponse/cbc:ReferenceID"/>
        </reference>
        <responseCode>
          <xsl:value-of select="cac:DiscrepancyResponse/cbc:ResponseCode"/>
        </responseCode>
        <responseDescription>
          <xsl:value-of select="cac:DiscrepancyResponse/cbc:Description"/>
        </responseDescription>
        <amount>
          <xsl:value-of select="cac:RequestedMonetaryTotal/cbc:PayableAmount"/>
        </amount>

        <thirdPartyDocumentType>
          <xsl:value-of select="cac:AccountingSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID/@schemeName"/>
        </thirdPartyDocumentType>
        <thirdPartyDocumentNumber>
          <xsl:value-of
            select="cac:AccountingSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:CompanyID"/>
        </thirdPartyDocumentNumber>
        <thirdPartyName>
          <xsl:value-of
            select="cac:AccountingSupplierParty/cac:Party/cac:PartyTaxScheme/cbc:RegistrationName"/>
        </thirdPartyName>

        <partyLegalEntityDocumentType>
          <xsl:value-of select="cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID/@schemeName"/>
        </partyLegalEntityDocumentType>
        <partyLegalEntityDocumentNumber>
          <xsl:value-of
                  select="cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:CompanyID"/>
        </partyLegalEntityDocumentNumber>
        <partyLegalEntityName>
          <xsl:value-of
                  select="cac:AccountingSupplierParty/cac:Party/cac:PartyLegalEntity/cbc:RegistrationName"/>
        </partyLegalEntityName>

      </xsl:for-each>
    </debitNote>
  </xsl:template>
</xsl:stylesheet>